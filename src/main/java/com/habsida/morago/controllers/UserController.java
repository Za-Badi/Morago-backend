package com.habsida.morago.controllers;

import com.habsida.morago.model.entity.User;
import com.habsida.morago.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/message")
    public ResponseEntity<String> defaultMessage() {
        return ResponseEntity.ok("This is Rixio");
    }

    @PostMapping("/phone")
    public ResponseEntity<String> changePhoneNumber(@RequestParam String newPhone) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        currentUser.setPhone(newPhone);
        userService.saveUser(currentUser);
        return ResponseEntity.status(HttpStatus.OK).body("Phone number updated successfully");
    }
    @PostMapping("/newPhone")
    public ResponseEntity<String> changePhoneNumber(@RequestBody Map<String, String> requestBody) {
        String newPhone = requestBody.get("newPhone");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        currentUser.setPhone(newPhone);
        userService.saveUser(currentUser);
        return ResponseEntity.status(HttpStatus.OK).body("Phone number updated successfully");
    }
}