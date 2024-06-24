package com.example.edumeeting.repositories;


import com.example.edumeeting.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

//    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    UserEntity findByEmail(String email);
}
