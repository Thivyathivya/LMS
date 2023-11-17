package com.LMS.userManagement.model;

import com.LMS.userManagement.enumFile.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;

    private String email;

    private  String password;

    private String confirmPassword;

    private String role;
}
