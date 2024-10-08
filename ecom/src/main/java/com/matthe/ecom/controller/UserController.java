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
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try{
            User user1 = userService.registerUser(user);
            return  new ResponseEntity<>(user1, HttpStatus.CREATED);
        }
        catch(Exception e){
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        // Perform authentication logic
        // Return appropriate response or token
        return ResponseEntity.ok("Login successful");
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Perform logout logic
        // Invalidate token or session
        return ResponseEntity.ok("Logout successful");
    }


}
