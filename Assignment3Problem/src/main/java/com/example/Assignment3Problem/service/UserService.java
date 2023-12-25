package com.example.Assignment3Problem.service;

import com.example.Assignment3Problem.model.User;
import com.example.Assignment3Problem.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String registerUser(String userId){
        if(userRepository.existsById(userId)){
            throw new RuntimeException("User with this id already exists");
        }
        //generating a user secret
        String userSecret = UUID.randomUUID().toString();
        //save user details
        User newUser = new User();
        newUser.setUserId(userId);
        newUser.setUserName(userSecret);
        newUser.setApproved(false);
        newUser.setRegistrationTime(LocalDateTime.now());
        userRepository.save(newUser);
        // return user secret
        return userSecret;
    }

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private long jwtExpiration;
    public String exchangeToken(String userSecret){
        //if user secret is valid
        User user = userRepository.findByUserSecret(userSecret);

        if(user!=null){
            //generate jwt token
            LocalDateTime now = LocalDateTime.now();
            Date expiration = new Date(now.toEpochSecond(null)*1000+jwtExpiration);

            String token = Jwts.builder().setSubject(user.getUserId())
                    .setIssuedAt(new Date()).setExpiration(expiration)
                    .signWith(SignatureAlgorithm.HS512,jwtSecret)
                    .compact();
            return token;
        }else{
            //handle invalid user secret
            throw new IllegalArgumentException("Invalid userSecret");
        }
    }
    public void approveUser(String userId){
        User user = userRepository.findByUserId(userId);

        if(user!=null && !user.isApproved()){
            user.setApproved(true);
            userRepository.save(user);
        }

    }
}
