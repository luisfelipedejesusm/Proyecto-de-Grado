package com.luisfelipedejesusm.final_project.DTOs.Models;

import com.luisfelipedejesusm.final_project.Models.Donation;
import com.luisfelipedejesusm.final_project.Models.DonationCenter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class DonationDTO {
    private Long id;
    private LocalDate dateAppointment;
    private LocalTime hourAppointment;
    private DonationCenterDTO donationCenter;
    private UserDTO user;

    private String user_firstname_fallback;
    private String user_lastname_fallback;

    public DonationDTO(Donation donation){
        this.id = donation.getId();
        this.dateAppointment = donation.getDateAppointment();
        this.hourAppointment = donation.getHourAppointment();
        this.donationCenter = new DonationCenterDTO(donation.getDonationCenter());

        if(donation.getUser() != null) {
            this.user = new UserDTO(donation.getUser());
        }else{
            this.user = new UserDTO(donation);
        }
    }
}
