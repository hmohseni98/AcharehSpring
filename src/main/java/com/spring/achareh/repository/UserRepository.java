package com.spring.achareh.repository;

import com.spring.achareh.model.Admin;
import com.spring.achareh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<S extends User> extends JpaRepository<S,Integer> {
    S findByEmailAndPassword(String email, String password);

    S findByEmail(String email);
}
