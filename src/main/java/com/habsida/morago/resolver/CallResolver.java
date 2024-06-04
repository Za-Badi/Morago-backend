package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CallResolver {
    private final CallService callService;
    private final UserRepository userRepository;


    @QueryMapping(name = "getCallByID")
    public Call getCall(@Argument Long id) {
        return callService.getCallsById(id);
    }

    @QueryMapping(name = "getAllCalls")
    public List<Call> getAllCalls() {
        return callService.getAllCalls();
    }

    @MutationMapping(name = "updateCall")
    public Call updateCall(@Argument Long id, @Argument Integer duration, @Argument String status, @Argument Double commission) {
        Call callDetails = new Call();
        callDetails.setDuration(duration);
        callDetails.setStatus(status);
        callDetails.setCommission(commission);
        return callService.updateCalls(id, callDetails);
    }

    @MutationMapping(name = "createCall")
    public Call createCall(@Argument("caller") User callerId, @Argument("recipient") User recipientId, @Argument("theme") Theme themeId) {
        Call call = new Call();
        call.setCaller(callerId);
        call.setRecipient(recipientId);
        call.setTheme(themeId);
        return callService.createCall(call);
    }


    @MutationMapping(name = "deleteCall")
    public Boolean deleteCall(@Argument Long id) {
        callService.deleteCall(id);
        return true;
    }
}