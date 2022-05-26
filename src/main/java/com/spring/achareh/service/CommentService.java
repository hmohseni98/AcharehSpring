package com.spring.achareh.service;

import com.spring.achareh.model.Comment;
import com.spring.achareh.service.base.BaseService;
import com.spring.achareh.service.dto.comment.CommentDTO;

import java.util.List;

public interface CommentService extends BaseService<Comment, Integer> {

    List<CommentDTO> findAllByExpertId(Integer expertId);

}
