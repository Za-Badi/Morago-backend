package com.habsida.morago.resolvers;

import com.habsida.morago.model.entity.User;
import com.habsida.morago.services.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class UserResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    private final UserService userService;
    @Autowired
    public UserResolver(UserService userService) {
        this.userService = userService;
    }
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    public Optional<User> getUserById(String id) throws Exception {
        return userService.getUserById(id);
    }
    public User addUser(User user) {
        return userService.addUser(user);
    }
    public User updateUser(String id, Map<String, Object> userInput) throws Exception {
        User userUpdate = new User();
        userUpdate.setPhone((String) userInput.get("phone"));
        userUpdate.setPassword((String) userInput.get("password"));
        userUpdate.setFirstName((String) userInput.get("firstName"));
        userUpdate.setLastName((String) userInput.get("lastName"));
        userUpdate.setBalance((Double) userInput.get("balance"));
        userUpdate.setFcmToken((String) userInput.get("fcmToken"));
        userUpdate.setApnToken((String) userInput.get("apnToken"));
        userUpdate.setRatings((Double) userInput.get("ratings"));
        userUpdate.setTotalRatings((Integer) userInput.get("totalRatings"));
        userUpdate.setIsActive((Boolean) userInput.get("isActive"));
        userUpdate.setIsDebtor((Boolean) userInput.get("isDebtor"));
        userUpdate.setOnBoardingStatus((Integer) userInput.get("onBoardingStatus"));
        return userService.updateUser(id, userUpdate);
    }
    public Boolean deleteUser(String id) throws Exception {
        userService.deleteUser(id);
        return true;
    }
}
