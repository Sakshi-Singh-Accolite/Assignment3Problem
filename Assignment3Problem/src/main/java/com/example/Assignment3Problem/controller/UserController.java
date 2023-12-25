package com.example.Assignment3Problem.controller;

import com.example.Assignment3Problem.request.TokenExchangeRequest;
import com.example.Assignment3Problem.request.UserApprovalRequest;
import com.example.Assignment3Problem.request.UserRegistrationRequest;
import com.example.Assignment3Problem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest request){
        String userSecret = userService.registerUser(request.getUserId());
        return ResponseEntity.ok("User registered. User Secret: "+userSecret);
    }

    @PostMapping("/token")
    public ResponseEntity<String> exchangeToken(@RequestBody TokenExchangeRequest request){
        String token= userService.exchangeToken(request.getUserSecret());
        return ResponseEntity.ok("JWT Token: "+ token);
    }

    @PostMapping("/admin/approve")
    public ResponseEntity<String> approvedUser(@RequestBody UserApprovalRequest request){
        userService.approveUser(request.getUserId());
        return ResponseEntity.ok("User Approved");
    }
}
