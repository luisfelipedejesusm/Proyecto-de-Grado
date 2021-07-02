package com.luisfelipedejesusm.final_project.DTOs.Models;

import com.luisfelipedejesusm.final_project.Models.Donation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
public class DonationDTO {
    private Long id;
    private Date dateAppointment;
    private Time hourAppointment;
    private String bloodBank;
    private String user;

    public DonationDTO(Donation donation){
        this.id = donation.getId();
        this.dateAppointment = donation.getDateAppointment();
        this.hourAppointment = donation.getHourAppointment();
        this.bloodBank = donation.getBloodBank().getName();
        this.user = donation.getUser().getName();
    }
}
