package com.luisfelipedejesusm.final_project.Controllers;

import com.luisfelipedejesusm.final_project.DTOs.Models.DonationDTO;
import com.luisfelipedejesusm.final_project.DTOs.Requests.DonationRequest;
import com.luisfelipedejesusm.final_project.Models.Donation;
import com.luisfelipedejesusm.final_project.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/donation")
    public List<DonationDTO> getAllDonations(){
        List<Donation> donations = appointmentService.getListOfDonations();
        return donations.stream().map(DonationDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/donation")
    public ResponseEntity<?> createNewDonationAppointment(@Valid @RequestBody DonationRequest request){
        appointmentService.createNewDonationAppointment(request);
        return ResponseEntity.ok("Donation Appointment Created Successfully");
    }
}
