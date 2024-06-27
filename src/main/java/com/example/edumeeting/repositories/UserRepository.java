package com.example.edumeeting.repositories;


import com.example.edumeeting.dtos.userdtos.UserDashboardDto;
import com.example.edumeeting.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

//    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    UserEntity findByEmail(String email);

    UserEntity findByConfirmationToken(String resetToken);



}
