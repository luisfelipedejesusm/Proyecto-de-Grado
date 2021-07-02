package com.luisfelipedejesusm.final_project.DTOs.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.sql.Date;

@Data
@AllArgsConstructor
public class DonationRequest {
    private Date dateAppointment;
    private Time hourAppointment;
    private Long bloodBankId;
}
