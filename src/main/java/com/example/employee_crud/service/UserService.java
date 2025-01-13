package com.example.employee_crud.service;


import com.example.employee_crud.entities.User;
import com.example.employee_crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User registerUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(int id)
    {
        userRepository.deleteById(id);
    }

    public Map<String, Object> verify(User user) {
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));


       if(authentication.isAuthenticated())
       {
           User authenticatedUser = userRepository.findByUsername(user.getUsername());
           if (authenticatedUser == null) {
               throw new RuntimeException("User not found!");
           }

           Map<String, Object> claims = new HashMap<>();
           claims.put("role", authenticatedUser.getRole());
           claims.put("email", authenticatedUser.getEmail());
           claims.put("name", authenticatedUser.getUsername());

           String token = jwtService.generateToken(user.getUsername(), claims);
           String refreshToken = jwtService.generateRefreshToken(user.getUsername());

           Map<String, Object> response = new HashMap<>();
           response.put("access_token", token);
           response.put("refresh_token", refreshToken);
           response.put("token_type", "Bearer");
           response.put("Access_Token_expires_in", jwtService.getTokenExpirationInSeconds());
           response.put("Refresh_Token_Expires_in",jwtService.getRefreshTokenExpirationInSeconds());
           response.putAll(claims);


           return response;
       }

        throw new RuntimeException("Authentication failed!");
    }
}
