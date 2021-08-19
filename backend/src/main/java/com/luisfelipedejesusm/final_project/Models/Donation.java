package com.luisfelipedejesusm.final_project.Models;

import com.luisfelipedejesusm.final_project.Enums.EBloodType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "donation_appointment")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateAppointment;
    private LocalTime hourAppointment;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "bloodBank_id", nullable = false)
//    private BloodBank bloodBank;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private EBloodType bloodGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donation_center_id", nullable = false)
    private DonationCenter donationCenter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean firstTimeDonor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    private boolean cancelled = false;

}
