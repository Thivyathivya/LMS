package com.LMS.userManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/lms/api/user")
public class UserController {



    @GetMapping("/userRead")
    @PreAuthorize("hasAuthority('user') or hasAuthority('admin') ")
    public String userRead(){

        return "User can  read";
    }
}
