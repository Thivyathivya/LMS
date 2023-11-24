package com.LMS.userManagement.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/lms/api/admin")
public class AdminController {


    @GetMapping("/adminRead")
   @PreAuthorize("hasAuthority('admin')")
    public String adminRead(){

        return "admin:: can  read";
    }

@PostMapping("/adminCreate")
    @PreAuthorize("hasAuthority('admin')")
    public String adminCreate(){

        return "admin:: can create";
    }

    @DeleteMapping("/adminDelete")
    @PreAuthorize("hasAuthority('admin')")
    public String adminDelete(){

        return "admin:: can  delete";
    }
}
