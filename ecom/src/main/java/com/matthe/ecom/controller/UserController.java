package com.matthe.ecom.controller;

import com.matthe.ecom.model.User;
import com.matthe.ecom.service.UserDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserDetailService userService;

    public UserController(UserDetailService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User user1 = userService.registerUser(user,"ROLE_USER", null);
            return new ResponseEntity<>(user1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody User user,@RequestParam String code) {
        try {
            User user1 = userService.registerUser(user,"ROLE_ADMIN",code);
            return new ResponseEntity<>(user1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        boolean authenticated = userService.authenticateUser(user.getUsername(), user.getPassword());
        if (authenticated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Perform logout logic
        return ResponseEntity.ok("Logout successful");
    }
}
