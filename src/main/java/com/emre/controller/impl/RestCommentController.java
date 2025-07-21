package com.emre.controller.impl;

import com.emre.controller.IRestCommentController;
import com.emre.dto.CommentResponse;
import com.emre.dto.DtoCommentIU;
import com.emre.dto.DtoCommentUpdate;
import com.emre.entities.Comment;
import com.emre.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class RestCommentController implements IRestCommentController {

    @Autowired
    private ICommentService commentService;

    @GetMapping
    @Override
    public List<CommentResponse> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return commentService.getAllCommentsWithParam(userId, postId);
    }

    @GetMapping("/{commentId}")
    @Override
    public Comment getOneComment(@PathVariable Long commentId) {
        return commentService.getOneCommentById(commentId);
    }

    @PostMapping
    @Override
    public Comment createOneComment(@RequestBody DtoCommentIU dtoCommentIU){
        return commentService.createOneComment(dtoCommentIU);
    }

    @PutMapping("/{commentId}")
    @Override
    public Comment updateOneComment(@PathVariable Long commentId,@RequestBody DtoCommentUpdate dtoCommentUpdate){
        return commentService.updateOneCommentById(commentId,dtoCommentUpdate);
    }

    @DeleteMapping("/{commentId}")
    @Override
    public void deleteOneComment(@PathVariable Long commentId){
         commentService.deleteOneCommentById(commentId);
    }



}
