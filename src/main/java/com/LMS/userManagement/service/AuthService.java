package com.LMS.userManagement.service;

import com.LMS.userManagement.config.AuthenticationResponse;
import com.LMS.userManagement.dto.ProfileDto;
import com.LMS.userManagement.dto.RegisterRequest;
import com.LMS.userManagement.model.*;
import com.LMS.userManagement.repository.QuizRankRepository;
import com.LMS.userManagement.repository.UserRepository;
import com.LMS.userManagement.securityConfig.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

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
    public ResponseEntity<String> register(RegisterRequest request) {


        User user=User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .confirmPassword(passwordEncoder.encode(request.getConfirmPassword()))
                .role("user")
                .createdDate(new Timestamp(System.currentTimeMillis()))
             //   .city(null)
             //   .country(null)
              //  .school(null)
             //   .standard(null)
             //   .gender(null)
                .build();
        userRepository.save(user);
      //  String jwtToken=jwtService.generateToken(user);
        return ResponseEntity.ok("Registered Successfully");
    }

    public AuthenticationResponse authentication(String email, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,password
                )
        );
      User user =userRepository.findByEmail(email);

        int goldCount = quizRankRepository.countByUserIdAndBadge(user.getId(), 1);
        int silverCount = quizRankRepository.countByUserIdAndBadge(user.getId(), 2);
        int bronzeCount = quizRankRepository.countByUserIdAndBadge(user.getId(), 3);
        Integer energyPoints = quizRankRepository.sumOfEnergyPoints(user.getId());
        String jwtToken=jwtService.generateToken(user);
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
                .build();
    }


}
