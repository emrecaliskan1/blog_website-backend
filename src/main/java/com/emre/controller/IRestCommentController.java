package com.emre.controller;

import com.emre.dto.DtoCommentIU;
import com.emre.dto.DtoCommentUpdate;
import com.emre.entities.Comment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface IRestCommentController {

    public List<Comment> getAllComments( Optional<Long> userId,  Optional<Long> postId);

    public Comment getOneComment( Long commentId);

    public Comment createOneComment( DtoCommentIU dtoCommentIU);

    public Comment updateOneComment( Long commentId, DtoCommentUpdate dtoCommentUpdate);

    public void deleteOneComment( Long commentId);
}
