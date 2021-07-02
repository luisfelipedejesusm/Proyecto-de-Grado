package com.luisfelipedejesusm.final_project.Repositories;

import com.luisfelipedejesusm.final_project.Models.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Long> {
}
