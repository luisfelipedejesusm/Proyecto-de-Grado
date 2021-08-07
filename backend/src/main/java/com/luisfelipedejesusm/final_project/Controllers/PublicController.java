package com.luisfelipedejesusm.final_project.Controllers;

import com.luisfelipedejesusm.final_project.DTOs.Models.BloodBankDTO;
import com.luisfelipedejesusm.final_project.DTOs.Models.DonationCenterDTO;
import com.luisfelipedejesusm.final_project.DTOs.Models.UserDTO;
import com.luisfelipedejesusm.final_project.DTOs.Requests.BloodBankRequest;
import com.luisfelipedejesusm.final_project.DTOs.Requests.DonationCenterRequest;
import com.luisfelipedejesusm.final_project.DTOs.Requests.DonationRequest;
import com.luisfelipedejesusm.final_project.DTOs.Requests.UserLatLng;
import com.luisfelipedejesusm.final_project.DTOs.Responses.AppointmentDateAndTime;
import com.luisfelipedejesusm.final_project.DTOs.Responses.MessageResponse;
import com.luisfelipedejesusm.final_project.Models.BloodBank;
import com.luisfelipedejesusm.final_project.Models.DonationCenter;
import com.luisfelipedejesusm.final_project.Models.User;
import com.luisfelipedejesusm.final_project.Services.AppointmentService;
import com.luisfelipedejesusm.final_project.Services.BloodBankService;
import com.luisfelipedejesusm.final_project.Services.DonationCenterService;
import com.luisfelipedejesusm.final_project.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("${endpoint}/public")
public class PublicController {
    @Autowired
    private DonationCenterService donationCenterService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @GetMapping("donation-center")
    public List<DonationCenterDTO> getAllDonationCenters(){
        List<DonationCenter> donationCenters = donationCenterService.getAllDonationCenters();
        return donationCenters.stream().map(DonationCenterDTO::new).collect(Collectors.toList());
    }

    @GetMapping("donors")
    public List<UserLatLng> getAllDonors(){
        List<User> donors = userService.getDonors();
        return donors.stream().map(UserLatLng::new).collect(Collectors.toList());
    }

    @GetMapping("/donation/appointment-dates/{id}")
    public List<AppointmentDateAndTime> getListOfDonationCenterAppointmentDates(@PathVariable Long id){
        return appointmentService.getDonationCenterAppointmentsDate(id);
    }

    @PostMapping("/donation")
    public MessageResponse createNewDonationAppointment(@Valid @RequestBody DonationRequest request){
        appointmentService.createNewDonationAppointment(request);
        return new MessageResponse("Donation Appointment Created Successfully");
    }
}
