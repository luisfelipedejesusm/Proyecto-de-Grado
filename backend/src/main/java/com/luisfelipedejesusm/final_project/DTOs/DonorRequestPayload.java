package com.luisfelipedejesusm.final_project.DTOs;

import com.luisfelipedejesusm.final_project.Enums.EBloodType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DonorRequestPayload {
    private EBloodType bloodType;
    private Long donorId;
    private String comments;
}
