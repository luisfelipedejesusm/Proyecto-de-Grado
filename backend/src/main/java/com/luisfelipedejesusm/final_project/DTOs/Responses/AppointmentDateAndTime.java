package com.luisfelipedejesusm.final_project.DTOs.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class AppointmentDateAndTime {
    private LocalDate date;
    private LocalTime time;
}
