package com.luisfelipedejesusm.final_project.Services;

import com.luisfelipedejesusm.final_project.DTOs.Models.DonationDTO;
import com.luisfelipedejesusm.final_project.DTOs.Requests.DonationRequest;
import com.luisfelipedejesusm.final_project.DTOs.Responses.AppointmentDateAndTime;
import com.luisfelipedejesusm.final_project.Models.BloodBank;
import com.luisfelipedejesusm.final_project.Models.Donation;
import com.luisfelipedejesusm.final_project.Models.DonationCenter;
import com.luisfelipedejesusm.final_project.Models.User;
import com.luisfelipedejesusm.final_project.Repositories.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    @Autowired
    private DonationRepository donationRepository;

    public void createNewDonationAppointment(DonationRequest request) {

//        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
        Donation donation = new Donation();
        donation.setFirstName(request.getFirstName());
        donation.setLastName(request.getLastName());
        donation.setEmail(request.getEmail());
        donation.setPhoneNumber(request.getPhoneNumber());
        donation.setAddress(request.getAddress());
        donation.setBloodGroup(request.getBloodGroup());
        donation.setDonationCenter(new DonationCenter(request.getDonationCenterId()));
        donation.setDateAppointment(request.getDateAppointment());
        donation.setHourAppointment(LocalTime.parse(request.getHourAppointment() + ":00"));
        donation.setFirstTimeDonor(request.getFirstTimeDonor());

        if(request.getUserid() != null){
            donation.setUser(new User(request.getUserid()));
        }

//        donation.setUser(userDetails.getUser());
//        donation.setDateAppointment(request.getDateAppointment());
//        donation.setHourAppointment(request.getHourAppointment());
//        donation.setBloodBank(new BloodBank(request.getBloodBankId()));
//
        donationRepository.save(donation);
    }

    public List<Donation> getListOfDonations() {
        return donationRepository.findAll();
    }

    public List<AppointmentDateAndTime> getDonationCenterAppointmentsDate(Long donationCenterId) {
        return donationRepository.findAllByDonationCenter(new DonationCenter(donationCenterId)).stream().map(appointment
                -> new AppointmentDateAndTime(appointment.getDateAppointment(), appointment.getHourAppointment()))
                .collect(Collectors.toList());
    }

    public List<Donation> getUserAppointments(Long userid) {
        return donationRepository.findAllByUserAndCancelled(new User(userid), false);
    }

    public DonationDTO getUserLastAppointment(Long id) {
        // TODO: Arreglar este desastre, debe ser el ultimo donation appointment al que fue
        return donationRepository.findFirstByUserAndCancelledOrderByIdAsc(new User(id), false);
    }

    public void cancel(Long id) {
        var donation = donationRepository.findById(id).orElse(null);
        if(donation != null){
            donation.setCancelled(true);
            donationRepository.save(donation);
        }
    }
}
