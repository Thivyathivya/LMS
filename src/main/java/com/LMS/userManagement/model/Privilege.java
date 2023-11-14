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
public class Privilege {


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String name;

        @ManyToMany(mappedBy = "privileges")
        private Collection<Role> roles;

}
