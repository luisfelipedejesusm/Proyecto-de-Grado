package com.luisfelipedejesusm.final_project.Controllers;

import com.luisfelipedejesusm.final_project.DTOs.Models.DonationDTO;
import com.luisfelipedejesusm.final_project.DTOs.Requests.DonationRequest;
import com.luisfelipedejesusm.final_project.DTOs.Responses.AppointmentDateAndTime;
import com.luisfelipedejesusm.final_project.DTOs.Responses.MessageResponse;
import com.luisfelipedejesusm.final_project.Models.Donation;
import com.luisfelipedejesusm.final_project.Services.AppointmentService;
import com.luisfelipedejesusm.final_project.Services.DonationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${endpoint}/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/donation")
    public List<DonationDTO> getAllDonations(){
        List<Donation> donations = appointmentService.getListOfDonations();
        return donations.stream().map(DonationDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/donation")
    public MessageResponse createNewDonationAppointment(@Valid @RequestBody DonationRequest request){
        appointmentService.createNewDonationAppointment(request);
        return new MessageResponse("Donation Appointment Created Successfully");
    }

    @GetMapping("/my-appointments/{id}")
    public List<DonationDTO> getMyAppointments(@PathVariable Long id){
        return appointmentService.getUserAppointments(id).stream().map(DonationDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/last-appointment/{id}")
    public DonationDTO getLastAppointment(@PathVariable Long id){
        return appointmentService.getUserLastAppointment(id);
    }

    @GetMapping("/donation/appointment-dates/{id}")
    public List<AppointmentDateAndTime> getListOfDonationCenterAppointmentDates(@PathVariable Long id){
        return appointmentService.getDonationCenterAppointmentsDate(id);
    }
}
