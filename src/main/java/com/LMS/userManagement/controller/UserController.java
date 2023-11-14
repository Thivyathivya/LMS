package com.LMS.userManagement.controller;

import com.LMS.userManagement.model.User;
import com.LMS.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/lms/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/userLogin")
    public ResponseEntity<?> userLogin(){
        return null;
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
