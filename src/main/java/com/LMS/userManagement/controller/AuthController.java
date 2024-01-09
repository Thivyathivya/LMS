package com.LMS.userManagement.controller;

import com.LMS.userManagement.config.AuthenticationResponse;
import com.LMS.userManagement.dto.RegisterRequest;
import com.LMS.userManagement.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/lms/api/auth")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequiredArgsConstructor
public class AuthController {


    @Autowired
    private  AuthService authService;




    @PostMapping("/register")
    public ResponseEntity<String> register (
                            @RequestBody RegisterRequest request){

    return authService.register(request);
}

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authentication (
            @RequestHeader String email,@RequestHeader String password) {
        AuthenticationResponse authenticationResponse
                =authService.authentication(email, password);
        return ResponseEntity.ok(authenticationResponse);
    }

  
    @PostMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        authService.refreshToken(request,response);
    }




}
