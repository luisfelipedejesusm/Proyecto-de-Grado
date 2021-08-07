package com.luisfelipedejesusm.final_project.DTOs.Requests;

import com.luisfelipedejesusm.final_project.Enums.EBloodType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DonationRequest {
    private String address;
    private EBloodType bloodGroup;
    private String email;
    private String firstName;
    private Boolean firstTimeDonor;
    private String lastName;
    private String phoneNumber;

    private LocalDate dateAppointment;
    private String hourAppointment;
    private Long donationCenterId;

    private Long userid;
}
