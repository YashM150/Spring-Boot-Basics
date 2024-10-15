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
        try {
            // Check the current session status
            String session = userService.checkSession(username);

            if (!Objects.equals(session, "LoggedIn")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not logged in.");
            }

            // Set the user status to LoggedOut
            String userStatus = userService.setuserStatus("LoggedOut", username);

//            // Check the updated session status
//            String log = userService.checkSession(userStatus);
//            if (Objects.equals(log, "LoggedOut")) {
                return ResponseEntity.ok("User successfully logged out.");
//            } else {
//                return ResponseEntity.internalServerError().body("Failed to log out the user.");
//            }

        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during logout.");
        }
    }

    @GetMapping("/session")
    public ResponseEntity<?> checkSession(@RequestParam("User") String username) {
        String session= userService.checkSession(username);
        if(Objects.equals(session, "LoggedIn"))
            return new ResponseEntity<>(session, HttpStatus.OK);// Session exists
        else{
            if(Objects.equals(session, "LoggedOut"))
                return new ResponseEntity<>(session, HttpStatus.OK);
            else
                return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> checkUser(@RequestParam("User") String username) {
        boolean User=userService.isAdmin(username);
        if(User==true)
            return new ResponseEntity<>(username, HttpStatus.OK);
        else
            return new ResponseEntity<>(username, HttpStatus.FORBIDDEN);
    }
}
