package com.LMS.userManagement.service;

import com.LMS.userManagement.model.User;
import com.LMS.userManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> saveUser(User user) {
      User userDetail=  userRepository.save(user);
       return  ResponseEntity.ok(userDetail);

    }
}
