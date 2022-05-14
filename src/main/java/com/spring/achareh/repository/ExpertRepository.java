package com.spring.achareh.repository;

import com.spring.achareh.model.Expert;
import com.spring.achareh.model.enumration.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Integer> {
    List<Expert> findAllByStatus(AccountStatus status);

    @Query(value = "select * from users " +
            "inner join expert_speciality es on users.id = es.expert_id " +
            "inner join speciality s on s.id = es.speciality_id " +
            "inner join category c on c.id = s.category_id " +
            "where c.name = :categoryName", nativeQuery = true)
    List<Expert> findAllByCategory(@Param("categoryName") String categoryName);
}