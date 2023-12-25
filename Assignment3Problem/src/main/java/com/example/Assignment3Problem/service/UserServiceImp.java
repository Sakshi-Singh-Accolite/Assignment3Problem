package com.example.Assignment3Problem.service;

import com.example.Assignment3Problem.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServiceImp {
    List<User> getAllUsers();
    ResponseEntity<String > approveUser(String userId);
    ResponseEntity<String> activateWallet(String userId);
    ResponseEntity<String> approvePayment(String userId, int option);
}
