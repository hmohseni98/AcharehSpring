package com.spring.achareh.repository;


import com.spring.achareh.model.Comment;
import com.spring.achareh.service.dto.comment.CommentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("select new com.spring.achareh.service.dto.comment.CommentDTO(s.name as serviceName , " +
            "c.description , " +
            "c.score , " +
            "c.submitDateTime as submitDate ) " +
            "from com.spring.achareh.model.Comment c " +
            "join c.offer o " +
            "join o.expert e " +
            "join o.order d " +
            "join d.speciality s " +
            "where e.id = ?1 ")
    List<CommentDTO> findAllByExpertId(Integer expertId);
}