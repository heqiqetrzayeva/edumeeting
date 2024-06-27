package com.example.edumeeting.repositories;

import com.example.edumeeting.models.Category;
import com.example.edumeeting.models.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query(value = "SELECT * FROM vacancies WHERE lower(name) LIKE %?1%", nativeQuery = true)
    List<Vacancy> findByName(String name);
}
