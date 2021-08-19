package com.luisfelipedejesusm.final_project.DTOs.Models;

import com.luisfelipedejesusm.final_project.Models.BloodBank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BloodBankDTO {
    private Long id;
    private String name;

    public BloodBankDTO(BloodBank bloodBank){
        this.id = bloodBank.getId();
        this.name = bloodBank.getName();
    }
}
