package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.enums.CallStatus;
import com.habsida.morago.model.inputs.CallUpdateInput;
import com.habsida.morago.model.inputs.CreateCallInput;
import com.habsida.morago.repository.ThemeRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CallMutationResolver implements GraphQLMutationResolver {
    private final CallService callService;
    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;

    public Call createCall(CreateCallInput input) throws Exception {
        return callService.createCall(input);
    }

    public Call updateCall(Long id, CallUpdateInput input ) {

        Float commission = (input.getCommission() != null) ? input.getCommission().floatValue() : null;

        return callService.updateCall(id, input.getStatus(), input.getDuration(), commission);
    }

    public Boolean deleteCall(Long id) throws Exception {
        callService.deleteCall(id);

        return true;
    }
}
