package com.spring.achareh.repository;


import com.spring.achareh.model.Comment;
import com.spring.achareh.model.Customer;
import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByCustomer(Customer customer);

    @Query(value = "select *  from comment " +
            "inner join orders o on o.id = comment.order_id " +
            "inner join speciality s on s.id = o.speciality_id " +
            "where s.id = :#{#speciality.id}", nativeQuery = true)
    List<Comment> findAllBySpeciality(@Param("speciality")Speciality speciality);
}
