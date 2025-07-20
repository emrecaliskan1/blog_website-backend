package com.emre.service.impl;

import com.emre.dto.*;
import com.emre.entities.Like;
import com.emre.entities.Post;
import com.emre.entities.User;
import com.emre.repository.LikeRepository;
import com.emre.repository.PostRepository;
import com.emre.service.ILikeService;
import com.emre.service.IPostService;
import com.emre.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private ILikeService likeService;

    //public void setLikeService(ILikeService likeService) {
        //this.likeService = likeService;
    //}


    @Override
    public List<DtoPostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent()) {
            list = postRepository.findByUserId(userId.get());
        } else {
            list = postRepository.findAll();
        }

       return list.stream().map(p -> {
           List<DtoLikeResponse> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(p.getId()));
           return new DtoPostResponse(p,likes);})
                .collect(Collectors.toList());
    }

    @Override
    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);

    }

    @Override
    public Post createOnePost(DtoPostIU dtoPostIU) {
        //DtoUser dtoUser = userService.getOneUserById(dtoPostIU.getUserId());
        User user = userService.getUserEntityById(dtoPostIU.getUserId());
        if (user == null) {
            return null;
        }
        //User user = new User();
        //BeanUtils.copyProperties(user, user);

        Post post = new Post();
        post.setTitle(dtoPostIU.getTitle());
        post.setText(dtoPostIU.getText());
        post.setUser(user);
        post.setCreateDate(new Date());
        return postRepository.save(post);
    }

    @Override
    public Post updateOnePost(Long postId, DtoPostUpdate dtoPostUpdate) {
        Optional<Post> optPost = postRepository.findById(postId);
        if(optPost.isPresent()){
            Post toUpdate = optPost.get();
            toUpdate.setText(dtoPostUpdate.getText());
            toUpdate.setTitle(dtoPostUpdate.getTitle());
            return postRepository.save(toUpdate);
        }

        return null;
    }

    @Override
    public void deleteOnePost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            postRepository.delete(post);
        }
    }


}
