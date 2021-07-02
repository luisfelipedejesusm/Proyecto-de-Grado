package com.luisfelipedejesusm.final_project.Services;

import com.luisfelipedejesusm.final_project.DTOs.Requests.DonationRequest;
import com.luisfelipedejesusm.final_project.Models.BloodBank;
import com.luisfelipedejesusm.final_project.Models.Donation;
import com.luisfelipedejesusm.final_project.Repositories.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private DonationRepository donationRepository;

    public void createNewDonationAppointment(DonationRequest request) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Donation donation = new Donation();
        donation.setUser(userDetails.getUser());
        donation.setDateAppointment(request.getDateAppointment());
        donation.setHourAppointment(request.getHourAppointment());
        donation.setBloodBank(new BloodBank(request.getBloodBankId()));

        donationRepository.save(donation);
    }

    public List<Donation> getListOfDonations() {
        return donationRepository.findAll();
    }
}
