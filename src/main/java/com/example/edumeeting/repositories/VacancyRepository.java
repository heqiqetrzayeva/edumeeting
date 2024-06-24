package com.example.edumeeting.repositories;

import com.example.edumeeting.models.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
}
