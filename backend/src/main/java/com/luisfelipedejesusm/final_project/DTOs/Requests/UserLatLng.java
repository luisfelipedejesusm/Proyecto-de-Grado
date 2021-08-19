package com.luisfelipedejesusm.final_project.DTOs.Requests;

import com.luisfelipedejesusm.final_project.Models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLatLng {
    private Double latitude;
    private Double longitude;

    public UserLatLng(User user){
        this.latitude = user.getLatitude();
        this.longitude = user.getLongitude();
    }
}
