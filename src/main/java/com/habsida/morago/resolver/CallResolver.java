package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.CallInput;
import com.habsida.morago.model.inputs.CallUpdateInput;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;


@Controller
public class CallResolver {
    private final CallService callService;

    @Autowired
    public CallResolver(CallService callService) {
        this.callService = callService;
    }
    @QueryMapping
    public List<Call> getAllCalls() {
        return callService.getAllCalls();
    }

    @QueryMapping
    public Optional<Call> getCallById(@Argument Long id) {
        return callService.getCallById(id);
    }
    @MutationMapping
    public Call createCall(@Argument Long caller, @Argument Long recipient, @Argument Long theme) throws Exception {
        CallInput callInput = new CallInput();
        callInput.setCallerId(caller);
        callInput.setRecipientId(recipient);
        callInput.setThemeId(theme);

        return callService.createCall(callInput);
    }

    @MutationMapping
    public Call updateCall(@Argument Long id, @Argument CallUpdateInput callDto) throws Exception {
        return callService.updateCall(id, callDto);
    }


    @MutationMapping
    public Boolean deleteCall(@Argument Long id) throws Exception{
        callService.deleteCall(id);
        return true;
    }
}