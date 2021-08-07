package com.luisfelipedejesusm.final_project.DTOs.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationCenterRequest {
    private String name;
    private String address;
    private String username;
    private String email;
    private String password;
    private Double latitude;
    private Double longitude;
}
