package com.spring.achareh.repository;


import com.spring.achareh.model.Category;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.service.dto.speciality.SpecialityDTO;
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

    @Query("select new com.spring.achareh.service.dto.speciality.SpecialityDTO(s.id , " +
            "s.name, " +
            "s.basePrice, " +
            "s.description, " +
            "c.name as categoryName ) " +
            "from com.spring.achareh.model.Speciality s " +
            "join s.category c")
    List<SpecialityDTO> selectAll();
}
