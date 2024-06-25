package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.UserDTO;
import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.serviceImpl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMutationResolver implements GraphQLMutationResolver {
    private final UserService userService;

    public UserDTO addUser(UserInput userInput) throws ExceptionGraphql {
        return userService.addUser(userInput);
    }

    public UserDTO updateUser(Long id, UserInput userInput) throws ExceptionGraphql {
        return userService.updateUser(id, userInput);
    }

    public Boolean deleteUser(Long id) throws ExceptionGraphql {
        userService.deleteUser(id);
        return true;
    }

    public Boolean changeIsActive(Long id, Boolean isActive) throws ExceptionGraphql {
        return userService.changeIsActive(id, isActive);
    }

    public Boolean changeIsDebtor(Long id, Boolean isDebtor) throws ExceptionGraphql {
        return userService.changeIsDebtor(id, isDebtor);
    }

    public UserDTO addFcmToken(String fcmToken, Long id) throws ExceptionGraphql {
        return userService.addFcmToken(fcmToken, id);
    }

    public Boolean deleteFcmToken(Long id) throws ExceptionGraphql {
        userService.deleteFcmToken(id);
        return true;
    }

    public UserDTO addApnToken(String apnToken, Long id) throws ExceptionGraphql {
        return userService.addApnToken(apnToken, id);
    }

    public Boolean deleteApnToken(Long id) throws ExceptionGraphql {
        userService.deleteApnToken(id);
        return true;
    }
}
