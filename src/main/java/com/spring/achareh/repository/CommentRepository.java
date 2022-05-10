package com.spring.achareh.repository;


import com.spring.achareh.model.Comment;
import com.spring.achareh.model.Customer;
import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByCustomer(Customer customer);

    List<Comment> findAllByExpert(Expert expert);

    List<Comment> findAllByService(Service service);
}
