package com.emre.service;

import com.emre.dto.DtoCommentIU;
import com.emre.dto.DtoCommentUpdate;
import com.emre.entities.Comment;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId);

    Comment getOneCommentById(Long commentId);

    Comment createOneComment(DtoCommentIU dtoCommentIU);

    Comment updateOneCommentById(Long commentId, DtoCommentUpdate dtoCommentUpdate);

    void deleteOneCommentById(Long commentId);
}
