package com.luisfelipedejesusm.final_project.Repositories;

import com.luisfelipedejesusm.final_project.Enums.EBloodType;
import com.luisfelipedejesusm.final_project.Enums.EUserType;
import com.luisfelipedejesusm.final_project.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    List<User> findByIsDonor(Boolean flag);

    List<User> findAllByBloodTypeAndUserType(EBloodType bloodType, EUserType user);

    List<User> findAllByUserType(EUserType user);
}
