package com.matthe.ecom.controller;

import com.matthe.ecom.model.User;
import com.matthe.ecom.service.UserDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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
            return new ResponseEntity<>(user1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody User user,@RequestParam("code") String code) {
        try {
            User user1 = userService.registerUser(user,"ROLE_ADMIN",code);
            return new ResponseEntity<>(user1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String Status="LoggedIn";
        boolean authenticated = userService.authenticateUser(user.getUsername(), user.getPassword());
        if (authenticated) {
            String userStatus = userService.setuserStatus(Status, user.getUsername());
            if(userStatus != null)
                return new ResponseEntity<>(userStatus, HttpStatus.OK);
            else{
                return new ResponseEntity<>(userStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam("User") String username) {
        // Perform logout logic
        String session= userService.checkSession(username);
        User user1=userService.getUser(username);
        String userStatus;
        if(Objects.equals(session, "LoggedIn"))
        {
            userStatus=userService.setuserStatus("LoggedOut",username);
            String session1= userService.checkSession(username);
            if (Objects.equals(session1, "LoggedOut"))
                return ResponseEntity.ok().build(); // Session exists
            else
                return ResponseEntity.internalServerError().build();

        }
        else{
            return ResponseEntity.internalServerError().build();
        }
    }
//    @GetMapping("/session")
//    public ResponseEntity<?> checkSession() {
//        String session= userService.checkSession();
//        if(Objects.equals(session, "LoggedIn"))
//            return ResponseEntity.ok().build(); // Session exists
//        else{
//            return ResponseEntity.internalServerError().build();
//        }
//    }
}
