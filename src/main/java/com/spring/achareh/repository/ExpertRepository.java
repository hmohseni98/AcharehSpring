package com.spring.achareh.repository;

import com.spring.achareh.model.Expert;
import com.spring.achareh.model.enumration.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Integer> {
    List<Expert> findAllByStatus(AccountStatus status);

    List<Expert> findAllByCategory(String categoryName);
}
