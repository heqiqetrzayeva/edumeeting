package com.example.edumeeting.repositories;

import com.example.edumeeting.models.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Page<Application> findByName(String name, Pageable pageable);

}

