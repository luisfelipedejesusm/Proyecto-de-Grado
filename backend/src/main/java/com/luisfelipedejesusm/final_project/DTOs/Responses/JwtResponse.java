package com.luisfelipedejesusm.final_project.DTOs.Responses;


import com.luisfelipedejesusm.final_project.DTOs.Models.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    //    private String type = "Bearer";
    private UserDTO user;
//    private Long id;
//    private String username;
//    private String email;
//    private List<String> roles;
}
