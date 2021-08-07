package com.luisfelipedejesusm.final_project.DTOs.Models;

import com.luisfelipedejesusm.final_project.Models.DonationCenter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DonationCenterDTO {
    private Long id;
    private String name;
    private String address;
    private String username;
    private String email;
    private Double latitude;
    private Double longitude;

    public DonationCenterDTO(DonationCenter donationCenter){
        this.username = donationCenter.getUser().getUsername();
        this.email= donationCenter.getUser().getEmail();
        this.id = donationCenter.getId();
        this.name = donationCenter.getName();
        this.address = donationCenter.getAddress();
        this.latitude = donationCenter.getLatitude();
        this.longitude = donationCenter.getLongitude();
    }
}
