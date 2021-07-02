package com.luisfelipedejesusm.final_project.DTOs.Models;

import com.luisfelipedejesusm.final_project.Models.Role;
import com.luisfelipedejesusm.final_project.Models.User;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private Boolean isDonor;
    private Set<String> roles;

    public UserDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.isDonor = user.getIsDonor();
        this.roles = user.getRoles().stream().map(Role::toString).collect(Collectors.toSet());
    }
}
