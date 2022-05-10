package com.spring.achareh.repository;


import com.spring.achareh.model.Category;
import com.spring.achareh.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
    List<Service> findAllByCategory(Category category);
}
