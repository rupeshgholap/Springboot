package com.example.employee_crud.controllers;

import com.example.employee_crud.entities.User;
import com.example.employee_crud.service.FileUploadService;
import com.example.employee_crud.service.JWTService;
import com.example.employee_crud.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    JWTService jwtService;

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
       return userService.registerUser(user);
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        Map<String, Object> response = userService.verify(user);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public void DeleteUser(@PathVariable int id)
    {
        userService.deleteUser(id);
    }
    @GetMapping("/home")
    public String home()
    {
        return "Welcome to Home Page";
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String,Object>> refreshToken(@RequestBody Map<String, String> refreshTokenRequest)
    {
        String refreshToken = refreshTokenRequest.get("refresh_token");
        if (refreshToken != null && jwtService.validateRefreshToken(refreshToken))
        {
            String username = jwtService.extractUsernameFromRefreshToken(refreshToken);
            String newAccessToken = jwtService.generateToken(username);
            Map<String, Object> response = new HashMap<>();
            response.put("access_token", newAccessToken);
            response.put("token_type", "Bearer");
            response.put("expires_in", jwtService.getTokenExpirationInSeconds());
            return ResponseEntity.ok(response);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid refresh token"));
        }
    }


    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        try {
            fileUploadService.saveFile(file, username);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }


}
