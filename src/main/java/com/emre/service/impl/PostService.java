package com.emre.service.impl;

import com.emre.dto.DtoPostIU;
import com.emre.dto.DtoPostUpdate;
import com.emre.dto.DtoUser;
import com.emre.entities.Post;
import com.emre.entities.User;
import com.emre.repository.PostRepository;
import com.emre.service.IPostService;
import com.emre.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private IUserService userService;


    @Override
    public List<Post> getAllPosts(Optional<Long> userId) {

        if(userId.isPresent()){
            return postRepository.findByUserId(userId.get());
        }

        return postRepository.findAll();
    }

    @Override
    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);

    }

    @Override
    public Post createOnePost(DtoPostIU dtoPostIU) {
        DtoUser dtoUser = userService.getOneUserById(dtoPostIU.getUserId());
        if (dtoUser == null) {
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(dtoUser, user);

        Post post = new Post();
        post.setId(dtoPostIU.getId());
        post.setTitle(dtoPostIU.getTitle());
        post.setText(dtoPostIU.getText());
        post.setUser(user);

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
