
package com.habsida.morago.serviceImpl;
import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.entity.Rating;
import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.CallInput;
import com.habsida.morago.model.inputs.CallUpdateInput;
import com.habsida.morago.model.inputs.RatingInput;
import com.habsida.morago.repository.CallRespository;
import com.habsida.morago.repository.RatingRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.CallService;
import com.habsida.morago.service.RatingService;
import com.habsida.morago.serviceImpl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CallServiceImpl implements CallService {
    private final CallRespository callRepository;
    private final UserService userService;

    private final ThemeService themeService;

//    @Autowired
//    public CallServiceImpl(CallRespository callRepository, UserService userService) {
//        this.callRepository = callRepository;
//        this.userService = userService;
//    }

    @Override
    public List<Call> getAllCalls() {
        return callRepository.findAll();
    }

    @Override
    public Optional<Call> getCallById(Long id) {
        return callRepository.findById(id);
    }



    @Override
    public Call createCall(CallInput callInput) throws Exception{

        User caller = userService.getUserById(callInput.getCallerId());
        if (caller == null) {
            throw new RuntimeException("Caller user not found with ID: " + callInput.getCallerId());
        }
        User recipient = userService.getUserById(callInput.getRecipientId());
        if (recipient == null) {
            throw new RuntimeException("Recipient user not found with ID: " + callInput.getRecipientId());

        }
        Theme theme = themeService.getThemeById(callInput.getThemeId());
        if (theme == null) {
            throw new RuntimeException("Theme not found with ID: " + callInput.getThemeId());
        }
        Call call = new Call();
        call.setCaller(caller);
        call.setRecipient(recipient);
        call.setTheme(theme);

        return callRepository.save(call);
    }


    @Override
    public Call updateCall(Long id, CallUpdateInput callUpdateInput) throws Exception {
        Call call = callRepository.findById(id).orElseThrow(() -> new Exception("Call not found"));
        if (callUpdateInput.getDuration() != null) {
            call.setDuration(callUpdateInput.getDuration());
        }
        if (callUpdateInput.getStatus() != null) {
            call.setStatus(callUpdateInput.getStatus());
        }
        if (callUpdateInput.getCommission() != null) {
            call.setCommission(callUpdateInput.getCommission());
        }
        return callRepository.save(call);
    }

    @Override
    public void deleteCall(Long id) throws Exception {
        Call call = callRepository.findById(id)
                .orElseThrow(() -> new Exception("Call not found for id: " + id));
        callRepository.delete(call);
    }
}