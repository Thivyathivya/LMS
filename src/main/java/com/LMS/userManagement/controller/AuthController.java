package com.LMS.userManagement.controller;

import com.LMS.userManagement.config.AuthenticationResponse;
import com.LMS.userManagement.dto.RegisterRequest;
import com.LMS.userManagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lms/api/auth")
@CrossOrigin(origins = "*")
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

        return ResponseEntity.ok(authService.authentication(email, password));
    }

    @GetMapping("/welcome")
    public String welcome(){
    return "Welcome";
    }

}
