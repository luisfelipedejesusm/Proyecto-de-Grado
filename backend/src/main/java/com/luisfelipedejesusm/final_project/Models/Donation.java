package com.luisfelipedejesusm.final_project.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "donation_appointment")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateAppointment;

    private Time hourAppointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bloodBank_id", nullable = false)
    private BloodBank bloodBank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
