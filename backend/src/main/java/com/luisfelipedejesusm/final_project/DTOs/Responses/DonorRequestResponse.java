package com.luisfelipedejesusm.final_project.DTOs.Responses;

import com.luisfelipedejesusm.final_project.Models.DonorRequest;
import lombok.Data;

@Data
public class DonorRequestResponse {
    private String bloodType;
    private Long id;
    private String comments;
    private Long user;
    private Long donor;

    public DonorRequestResponse(DonorRequest donorRequest){
        this.bloodType = donorRequest.getBloodType().toString();
        this.id = donorRequest.getId();
        this.comments = donorRequest.getComments();
        this.user = donorRequest.getUser().getId();
        this.donor = donorRequest.getDonor().getId();
    }
}
