package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {



        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String name;
        @ManyToMany(mappedBy = "roles")
        private Collection<Student> student;

        @ManyToMany
        @JoinTable(
                name = "roles_privileges",
                joinColumns = @JoinColumn(
                        name = "role_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(
                        name = "privilege_id", referencedColumnName = "id"))
        private Collection<Privilege> privileges;
    }

