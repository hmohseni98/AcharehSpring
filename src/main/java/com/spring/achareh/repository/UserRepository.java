package com.spring.achareh.repository;

import com.spring.achareh.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {
    User findByEmailAndPassword(String email, String password);

    List<User> findAll(Specification<User> specification);
}
