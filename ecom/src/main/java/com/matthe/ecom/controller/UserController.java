package com.matthe.ecom.controller;

import com.matthe.ecom.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestPart String Username, @RequestPart String Password){

        return  new ResponseEntity<>("Logged In", HttpStatus.OK);
    }

}
