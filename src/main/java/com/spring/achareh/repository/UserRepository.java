package com.spring.achareh.repository;

import com.spring.achareh.model.User;
import com.spring.achareh.service.dto.user.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {
    User findByEmailAndPassword(String email, String password);

    @Query("select new com.spring.achareh.service.dto.user.UserDTO(u.id , " +
            "u.firstName, " +
            "u.lastName, " +
            "u.email, " +
            "u.role ) " +
            "from com.spring.achareh.model.User u")
    Page<UserDTO> selectAll(Specification<UserDTO> specification, Pageable pageable);

    Boolean existsUserByEmail(String email);

    Optional<User> findByEmail(String email);
}
