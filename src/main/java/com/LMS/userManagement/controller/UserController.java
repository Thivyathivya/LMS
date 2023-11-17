package com.LMS.userManagement.controller;

import com.LMS.userManagement.model.User;
import com.LMS.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/lms/api/demo")
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

    @GetMapping("/userRead")
    @PreAuthorize("hasAuthority('user')")
    public String userRead(){

        return "User can  read";
    }
}
