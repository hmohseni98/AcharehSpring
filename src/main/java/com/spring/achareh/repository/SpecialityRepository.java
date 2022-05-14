package com.spring.achareh.repository;


import com.spring.achareh.model.Category;
import com.spring.achareh.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {
    List<Speciality> findAllByCategory(Category category);

    @Query(value = "select * from speciality inner join expert_speciality es on speciality.id = es.speciality_id " +
            "where es.expert_id = ?1",nativeQuery = true)
    Set<Speciality> findSpecialityByExpertId(Integer expertId);
}
