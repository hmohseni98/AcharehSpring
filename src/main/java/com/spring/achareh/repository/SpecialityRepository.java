package com.spring.achareh.repository;


import com.spring.achareh.model.Category;
import com.spring.achareh.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {
    List<Speciality> findAllByCategory(Category category);
}
