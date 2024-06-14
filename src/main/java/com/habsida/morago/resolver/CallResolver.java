//package com.habsida.morago.resolver;
//
//import com.habsida.morago.model.entity.Call;
//import com.habsida.morago.model.entity.Theme;
//import com.habsida.morago.model.entity.User;
//import com.habsida.morago.model.enums.CallStatus;
//import com.habsida.morago.model.inputs.CallInput;
//import com.habsida.morago.repository.ThemeRepository;
//import com.habsida.morago.repository.UserRepository;
//import com.habsida.morago.service.CallService;
//import lombok.RequiredArgsConstructor;
//import org.checkerframework.checker.units.qual.A;
//import org.springframework.graphql.data.method.annotation.Argument;
//import org.springframework.graphql.data.method.annotation.MutationMapping;
//import org.springframework.graphql.data.method.annotation.QueryMapping;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//
//import java.util.List;
//
//
//@Controller
//@Component
//@RequiredArgsConstructor
//public class CallResolver {
//    private final CallService callService;
//
//    private final UserRepository userRepository;
//    private final ThemeRepository themeRepository;
//
//    @QueryMapping(name = "getAllCalls")
//    public List<Call> getAllCalls() {
//        return callService.getAllCalls();
//    }
//
//    @QueryMapping(name = "getCallById")
//    public Call getCallById(@Argument Long id) throws Exception {
//        return callService.getCallById(id);
//    }
//    @MutationMapping(name ="createCall")
//    public Call createCall(@Argument("CallInput") CallInput callInput) throws Exception {
//        User caller = userRepository.findById(callInput.getCallerId()).orElseThrow(() -> new RuntimeException("Caller not found"));
//        User recipient = userRepository.findById(callInput.getRecipientId()).orElseThrow(() -> new RuntimeException("Recipient not found"));
//        Theme theme = themeRepository.findById(callInput.getThemeId()).orElseThrow(() -> new RuntimeException("Theme not found"));
//
//        Call call = new Call();
//        call.setCaller(caller);
//        call.setRecipient(recipient);
//        call.setTheme(theme);
//
//        return callService.createCall(callInput);
//    }
//
//
//    @MutationMapping(name = "updateCall")
//    public Call updateCall(@Argument Long id, @Argument CallStatus status, @Argument Integer duration, @Argument Float commission ) throws  Exception{
//        return callService.updateCall(id, status, duration, commission);
//    }
//
//    @MutationMapping(name = "deleteCall")
//    public Boolean deleteCall(@Argument Long id) throws Exception{
//        callService.deleteCall(id);
//        return true;
//    }
//
//
//    @QueryMapping(name = "getCallsByStatus")
//    public List<Call> getCallsByStatus(@Argument CallStatus status) {
//        return callService.getCallByStatus(status);
//    }
//
//    @QueryMapping(name = "getAllMissedCalls")
//    public List<Call> getAllMissedCalls(@Argument CallStatus callStatus) {
//        return callService.getAllMissedCall(callStatus);
//    }
//
//    @QueryMapping(name = "getCallsByUserId")
//    public List<Call> getCallsByUserId(@Argument Long userId) {
//        return callService.getCallByUserId(userId);
//    }
//
//
//}