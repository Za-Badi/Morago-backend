package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.dto.CallDTO;
import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.enums.CallStatus;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.enums.UserStatus;
import com.habsida.morago.model.inputs.CreateCallInput;
import com.habsida.morago.model.inputs.CreateDepositsInput;
import com.habsida.morago.repository.CallRepository;
import com.habsida.morago.repository.ThemeRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.CallService;
import com.habsida.morago.service.DepositsService;
import com.habsida.morago.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CallServiceImpl implements CallService {
    private final CallRepository callRepository;
    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;
    private final NotificationService notificationService;
    private final DepositsService depositsService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public List<CallDTO> getAllCalls() {
        List<Call> calls = callRepository.findAll();
        return calls.stream()
                .map(call -> modelMapper.map(call, CallDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<CallDTO> getAllFreeCall() {
        List<Call> calls = callRepository.getFreeCallIsMade();
        return calls.stream()
                .map(call -> modelMapper.map(call, CallDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CallDTO getCallById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        Call call = callRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Call not found with id: " + id));
        return modelMapper.map(call, CallDTO.class);
    }


    @Override
    @Transactional
    public CallDTO createCall(String channelName, Long translatorId, Long userId, Long themeId) throws Exception {
        // Retrieve user and theme
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new IllegalArgumentException("Theme not found with id: " + themeId));

        // Attempt to find the specified translator and check status
        User translator = userRepository.findById(translatorId)
                .orElseThrow(() -> new IllegalArgumentException("Translator not found with id: " + translatorId));
        if (translator.getStatus() != UserStatus.ONLINE) {
            translator = findNextAvailableTranslator(themeId);
        }

        // Create call
        Call call = new Call();
        call.setCaller(user);
        call.setRecipient(translator);
        call.setTheme(theme);
        call.setCreatedAt(LocalDateTime.now());
        call.setStatus(CallStatus.OUTGOING_CALL);
        callRepository.save(call);

        // Notify users
        notificationService.notifyCallCreation(translator, user);

        // Update translator status
        translator.setStatus(UserStatus.BUSY);
        userRepository.save(translator);

        return modelMapper.map(call, CallDTO.class);
    }

    private User findNextAvailableTranslator(Long themeId) {
        return userRepository.findAvailableTranslatorByThemeId(themeId)
                .orElseThrow(() -> new IllegalArgumentException("No available translator found for theme id: " + themeId));
    }

    @Override
    @Transactional
    public CallDTO updateCall(Long id, CallStatus status, Integer duration, Float commission) {
        Call call = callRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Call not found with id: " + id));

        if (status != null && !status.toString().isEmpty()) {
            call.setStatus(status);
        } else {
            throw new IllegalArgumentException("Status can't be null or empty");
        }

        if (duration != null) {
            if (duration == 0) {
                throw new IllegalArgumentException("Duration must be greater than zero.");
            } else {
                call.setDuration(duration);
            }
        } else {
            throw new IllegalArgumentException("Duration cannot be null.");
        }

        if (commission != null) {
            if (commission >= 0.0f) {
                call.setCommission(commission.doubleValue());
            } else {
                throw new IllegalArgumentException("Commission must be greater than zero.");
            }
        }
        call.setUpdatedAt(LocalDateTime.now());

        Call updatedCall = callRepository.save(call);
        return modelMapper.map(updatedCall, CallDTO.class);
    }


    @Override
    @Transactional
    public void endCall(Long callId, CallStatus status, Integer duration) {
        Call call = callRepository.findById(callId)
                .orElseThrow(() -> new IllegalArgumentException("Call not found with id: " + callId));
        call.setStatus(status);
        call.setDuration(duration);
        call.setUpdatedAt(LocalDateTime.now());

        // Financial transactions
        double callCost = calculateCallCost(duration, call.getTheme().getPrice());
        deductUserBalance(call.getCaller(), callCost);
        addTranslatorBalance(call.getRecipient(), callCost * 0.85);

        // Update translator status
        call.getRecipient().setStatus(UserStatus.ONLINE);
        userRepository.save(call.getRecipient());

        callRepository.save(call);

        // Notify users about call end
        notificationService.notifyCallEnd(call.getCaller(), call.getRecipient(), call);
    }

    private double calculateCallCost(Integer duration, BigDecimal pricePerMinute) {
        return duration * pricePerMinute.doubleValue();
    }

    private void deductUserBalance(User user, double amount) {
        CreateDepositsInput deductInput = new CreateDepositsInput();
        deductInput.setUserId(user.getId());
        deductInput.setCoin(-amount);  // Assuming negative value deducts the amount
        deductInput.setStatus(PaymentStatus.COMPLETED);
        depositsService.addDeposit(deductInput);
    }

    private void addTranslatorBalance(User translator, double amount) {
        CreateDepositsInput addInput = new CreateDepositsInput();
        addInput.setUserId(translator.getId());
        addInput.setCoin(amount);
        addInput.setStatus(PaymentStatus.COMPLETED);
        depositsService.addDeposit(addInput);
    }

    @Override
    @Transactional
    public void rateCall(Long userId, Long callId, double grade) {
        Call call = callRepository.findById(callId)
                .orElseThrow(() -> new IllegalArgumentException("Call not found with id: " + callId));
        User whoUser = userRepository.findById(userId)
                .orElseThrow(() -> new GraphqlException("User who has rated not found"));

        if (whoUser.getUserProfile() != null) {
            if (call.getUserHasRated()) {
                throw new IllegalArgumentException("User has already rated this call.");
            } else {
                call.setUserHasRated(true);
                // Update the ratings for the translator
                User translator = call.getRecipient();
                translator.setRatings((translator.getRatings() * translator.getTotalRatings() + grade) / (translator.getTotalRatings() + 1));
                translator.setTotalRatings(translator.getTotalRatings() + 1);
                userRepository.save(translator);

                callRepository.save(call);
            }
        }

        if (whoUser.getTranslatorProfile() != null || whoUser.getConsultantProfile() != null) {
                if (whoUser.getTranslatorProfile() != null) {
                    if (call.getTranslatorHasRated()) {
                        throw new IllegalArgumentException("User has already rated this call.");
                    } else {
                        call.setTranslatorHasRated(true);
                    }
                }
                if (whoUser.getConsultantProfile() != null) {
                    if (call.getConsultantHasRated()) {
                        throw new IllegalArgumentException("User has already rated this call.");
                    } else {
                        call.setConsultantHasRated(true);
                    }
                }

                // Update the ratings for the caller
                User caller = call.getCaller();
                caller.setRatings((caller.getRatings() * caller.getTotalRatings() + grade) / (caller.getTotalRatings() + 1));
                caller.setTotalRatings(caller.getTotalRatings() + 1);

                userRepository.save(caller);
                callRepository.save(call);
        }
    }
    @Override
    @Transactional
    public void deleteCall(Long id) {
        callRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<CallDTO> getCallsByUserId(Long userId) {
        List<Call> calls = callRepository.findCallsByUserId(userId);
        if (calls == null || calls.isEmpty()) {
            throw new IllegalArgumentException("Calls not found for user with id: " + userId);
        }
        return calls.stream()
                .map(call -> modelMapper.map(call, CallDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Page<CallDTO> getAllMissedCalls(Long userId, Pageable pageable) {
        return callRepository.getAllMissedCalls(userId, pageable)
                .map(call -> modelMapper.map(call, CallDTO.class));
    }

    @Override
    @Transactional
    public Page<CallDTO> getCallsByOutgoingIncomingStatus(CallStatus status, Pageable pageable) {
        return callRepository.getCallsByOutgoingIncomingStatus(status, pageable)
                .map(call -> modelMapper.map(call, CallDTO.class));
    }

    @Override
    @Transactional
    public Page<CallDTO> getCallsByOutgoingIncoming(Pageable pageable) {
        return callRepository.getCallsByOutgoingIncoming(pageable)
                .map(call -> modelMapper.map(call, CallDTO.class));
    }

    @Override
    @Transactional
    public CallDTO createConsultantCall(String channelName, Long translatorId, Long userId, Long consultantId, Long themeId) throws Exception {
        // Retrieve user and theme
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new IllegalArgumentException("Theme not found with id: " + themeId));

        // Attempt to find the specified translator and check status
        User translator = userRepository.findById(translatorId)
                .orElseThrow(() -> new IllegalArgumentException("Translator not found with id: " + translatorId));
        if (translator.getStatus() != UserStatus.ONLINE) {
            translator = findNextAvailableTranslator(themeId);
        }

        // Attempt to find the specified consultant and check status
        User consultant = userRepository.findById(consultantId)
                .orElseThrow(() -> new IllegalArgumentException("Consultant not found with id: " + consultantId));
        if (consultant.getStatus() != UserStatus.ONLINE) {
            consultant = findNextAvailableConsultant(themeId);
        }

        // Create call
        Call call = new Call();
        call.setCaller(user);
        call.setRecipient(translator);
        call.setRecipientConsultant(consultant);
        call.setTheme(theme);
        call.setCreatedAt(LocalDateTime.now());
        call.setStatus(CallStatus.OUTGOING_CALL);
        callRepository.save(call);

        // Notify users
        notificationService.notifyConsultantCallCreation(translator, user, consultant);

        // Update translator and consultant status
        translator.setStatus(UserStatus.BUSY);
        userRepository.save(translator);
        consultant.setStatus(UserStatus.BUSY);
        userRepository.save(consultant);

        return modelMapper.map(call, CallDTO.class);
    }

    private User findNextAvailableConsultant(Long themeId) {
        return userRepository.findAvailableConsultantByThemeId(themeId)
                .orElseThrow(() -> new IllegalArgumentException("No available consultant found for theme id: " + themeId));
    }

    @Override
    @Transactional
    public void endConsultantCall(Long callId, CallStatus status, Integer duration) {
        Call call = callRepository.findById(callId)
                .orElseThrow(() -> new IllegalArgumentException("Call not found with id: " + callId));
        call.setStatus(status);
        call.setDuration(duration);
        call.setUpdatedAt(LocalDateTime.now());

        // Financial transactions
        double translatorCallCost = calculateCallCost(duration, call.getTheme().getPrice());
        double consultantCallCost = calculateCallCost(duration, call.getTheme().getConsultantPrice());
        double totalCallCost = translatorCallCost + consultantCallCost;
        deductUserBalance(call.getCaller(), totalCallCost);
        // For translator
        addTranslatorBalance(call.getRecipient(), translatorCallCost * 0.85);
//      // For consultant
        addTranslatorBalance(call.getRecipientConsultant(), consultantCallCost * 0.85);

        // Update translator status
        call.getRecipient().setStatus(UserStatus.ONLINE);
        userRepository.save(call.getRecipient());
        call.getRecipientConsultant().setStatus(UserStatus.ONLINE);
        userRepository.save(call.getRecipientConsultant());

        callRepository.save(call);

        // Notify users about call end
        notificationService.notifyConsultantCallEnd(call.getCaller(), call.getRecipient(), call.getRecipientConsultant() , call);
    }

    @Override
    @Transactional
    public void rateConsultantCall(Long userId, Long callId, double translatorGrade, double consultantGrade) {
        Call call = callRepository.findById(callId)
                .orElseThrow(() -> new IllegalArgumentException("Call not found with id: " + callId));
        User whoUser = userRepository.findById(userId)
                .orElseThrow(() -> new GraphqlException("User who has rated not found"));

        if (whoUser.getUserProfile() != null) {
            if (call.getUserHasRated()) {
                throw new IllegalArgumentException("User has already rated this call.");
            } else {
                call.setUserHasRated(true);
                // Update the ratings for the translator
                User translator = call.getRecipient();
                translator.setRatings((translator.getRatings() * translator.getTotalRatings() + translatorGrade) / (translator.getTotalRatings() + 1));
                translator.setTotalRatings(translator.getTotalRatings() + 1);
                userRepository.save(translator);

                // Update the ratings for the consultant
                User consultant = call.getRecipientConsultant();
                consultant.setRatings((consultant.getRatings() * consultant.getTotalRatings() + consultantGrade) / (consultant.getTotalRatings() + 1));
                consultant.setTotalRatings(consultant.getTotalRatings() + 1);
                userRepository.save(consultant);

                callRepository.save(call);
            }
        } else {
            throw new GraphqlException("User does not have a User profile.");
        }
    }
}
