package com.LMS.userManagement.service;

import com.LMS.userManagement.config.AuthenticationResponse;
import com.LMS.userManagement.dto.RegisterRequest;
import com.LMS.userManagement.model.*;
import com.LMS.userManagement.repository.QuizRankRepository;
import com.LMS.userManagement.repository.TokenRepository;
import com.LMS.userManagement.repository.UserRepository;
import com.LMS.userManagement.securityConfig.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    QuizRankRepository quizRankRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final TokenRepository tokenRepository;
    public ResponseEntity<String> register(RegisterRequest request) {


        User user=User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .confirmPassword(passwordEncoder.encode(request.getConfirmPassword()))
                .role("user")
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .build();
       var savedUser= userRepository.save(user);
        return ResponseEntity.ok("Registered Successfully");
    }



    public AuthenticationResponse authentication(String email, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,password
                )
        );
      var user =userRepository.findByEmail(email).orElseThrow();
        int goldCount = quizRankRepository.countByUserIdAndBadge(user.getId(), 1);
        int silverCount = quizRankRepository.countByUserIdAndBadge(user.getId(), 2);
        int bronzeCount = quizRankRepository.countByUserIdAndBadge(user.getId(), 3);
        Integer energyPoints = quizRankRepository.sumOfEnergyPoints(user.getId());
        String jwtToken=jwtService.generateToken(user);
       // revokeAllUserTokens(user);
        //saveUserToken(user, jwtToken);

        String refreshToken=jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole())
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .gold(goldCount)
                .silver(silverCount)
                .bronze(bronzeCount)
                .energyPoints(energyPoints)
                .refreshToken(refreshToken)
                .build();
    }


    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            return;
        }

        refreshToken=authHeader.substring(7);
        userEmail=jwtService.extractUsername(refreshToken);
        if(userEmail!=null){
            var user=this.userRepository.findByEmail(userEmail).orElseThrow();
            if(jwtService.isTokenValid(refreshToken,user)){
                String accessToken=jwtService.generateToken(user);
               // revokeAllUserTokens(user);
              //  saveUserToken(user,accessToken);
               var authResponse= AuthenticationResponse.builder()
                        .token(accessToken)
                        .refreshToken(refreshToken)
                        .build();

                new ObjectMapper().writeValue(response.getOutputStream(),authResponse);
            }
        }

    }
    private void saveUserToken(User user, String jwtToken) {
        var token=Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType("BEARER")
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user){
        var validUserTokens=tokenRepository.findAllvalidTokensByUser(user.getId());
        if(validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t -> {
            t.setRevoked(true);
            t.setExpired(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
