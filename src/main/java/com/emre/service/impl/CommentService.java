package com.emre.service.impl;

import com.emre.dto.CommentResponse;
import com.emre.dto.DtoCommentIU;
import com.emre.dto.DtoCommentUpdate;
import com.emre.dto.DtoUser;
import com.emre.entities.Comment;
import com.emre.entities.Post;
import com.emre.repository.CommentRepository;
import com.emre.service.ICommentService;
import com.emre.service.IPostService;
import com.emre.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IPostService postService;

    @Override
    public List<CommentResponse> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Comment> comments;
        if(userId.isPresent() && postId.isPresent()) {
            comments = commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            comments= commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            comments= commentRepository.findByPostId(postId.get());
        } else {
            comments= commentRepository.findAll();
        }
        return comments.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());
    }

    @Override
    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElse(null);
    }

    @Override
    public Comment createOneComment(DtoCommentIU dtoCommentIU) {
        DtoUser dtoUser = userService.getOneUserById(dtoCommentIU.getUserId());
        Post post = postService.getOnePostById(dtoCommentIU.getPostId());
        if (dtoUser != null && post != null) {
            Comment commentToSave = new Comment();
            //commentToSave.setId(dtoCommentIU.getId());
            commentToSave.setPost(post);
            commentToSave.setText(dtoCommentIU.getText());
            commentToSave.setUser(dtoUser.toUser());
            commentToSave.setCreateDate(new Date());
            return commentRepository.save(commentToSave);
        }
        return null;
    }

    @Override
    public Comment updateOneCommentById(Long commentId, DtoCommentUpdate dtoCommentUpdate) {
        Optional<Comment> optComment = commentRepository.findById(commentId);
        if (optComment.isPresent()){
            Comment commentToUpdate = optComment.get();
            commentToUpdate.setText(dtoCommentUpdate.getText());
            commentRepository.save(commentToUpdate);
            return  commentToUpdate;
        }

        return null;
    }

    @Override
    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }



}
