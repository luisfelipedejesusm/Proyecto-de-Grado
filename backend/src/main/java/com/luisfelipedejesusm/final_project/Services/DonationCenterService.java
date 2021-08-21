package com.luisfelipedejesusm.final_project.Services;

import com.luisfelipedejesusm.final_project.DTOs.Models.DonationCenterDTO;
import com.luisfelipedejesusm.final_project.DTOs.Requests.BloodBankRequest;
import com.luisfelipedejesusm.final_project.DTOs.Requests.DonationCenterRequest;
import com.luisfelipedejesusm.final_project.DTOs.Requests.RegisterRequest;
import com.luisfelipedejesusm.final_project.DTOs.Responses.AppointmentDateAndTime;
import com.luisfelipedejesusm.final_project.Enums.EUserType;
import com.luisfelipedejesusm.final_project.Models.BloodBank;
import com.luisfelipedejesusm.final_project.Models.Donation;
import com.luisfelipedejesusm.final_project.Models.DonationCenter;
import com.luisfelipedejesusm.final_project.Repositories.BloodBankRepository;
import com.luisfelipedejesusm.final_project.Repositories.DonationCenterRepository;
import com.luisfelipedejesusm.final_project.Repositories.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationCenterService {
    @Autowired
    private DonationCenterRepository donationCenterRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private DonationRepository donationRepository;

    public Long createNewDonationCenter(DonationCenterRequest request) {

        RegisterRequest registerRequest = new RegisterRequest(
                request.getName(),
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );

        var user = authenticationService.registerUser(registerRequest, EUserType.DONATION_CENTER);

        DonationCenter donationCenter = new DonationCenter();
        donationCenter.setName(request.getName());
        donationCenter.setAddress(request.getAddress());
        donationCenter.setLatitude(request.getLatitude());
        donationCenter.setLongitude(request.getLongitude());
        donationCenter.setUser(user);

        return donationCenterRepository.save(donationCenter).getId();
    }

    public List<DonationCenter> getAllDonationCenters() {
        return donationCenterRepository.findAll();
    }

    public void updateDonationCenter(Long id, DonationCenterRequest request) {
        DonationCenter donationCenter = donationCenterRepository.findById(id).orElse(null);
        if(donationCenter != null){
            donationCenter.setName(request.getName());
            donationCenter.setAddress(request.getAddress());
            donationCenter.setLatitude(request.getLatitude());
            donationCenter.setLongitude(request.getLongitude());
            donationCenterRepository.save(donationCenter);
        }
    }

    public void deleteDonationCenter(Long id) {
        if(donationCenterRepository.existsById(id))
            donationCenterRepository.deleteById(id);
    }

    public List<Donation> getAllAppointments() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DonationCenter donationCenter = donationCenterRepository.findByUserId(userDetails.getId());

        return donationRepository.findAllByDonationCenterAndCancelled(donationCenter, false);
    }
}
