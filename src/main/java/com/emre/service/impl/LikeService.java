package com.emre.service.impl;

import com.emre.dto.DtoLikeIU;
import com.emre.dto.DtoUser;
import com.emre.entities.Like;
import com.emre.entities.Post;
import com.emre.entities.User;
import com.emre.repository.LikeRepository;
import com.emre.service.ILikeService;
import com.emre.service.IPostService;
import com.emre.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService implements ILikeService {

    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IPostService postService;

    @Override
    public List<DtoLikeIU> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if(userId.isPresent() && postId.isPresent()) {
            list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()) {
            list = likeRepository.findByUserId(userId.get());
        }else if(postId.isPresent()) {
            list = likeRepository.findByPostId(postId.get());
        }else
            list = likeRepository.findAll();
        return list.stream().map(like -> new DtoLikeIU(like)).collect(Collectors.toList());
    }

    @Override
    public Like createOneLike(DtoLikeIU request) {
        DtoUser dtoUser = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if(dtoUser != null && post != null) {
            Like likeToSave = new Like();
            likeToSave.setId(request.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(dtoUser.toUser());
            return likeRepository.save(likeToSave);
        }else
            return null;
    }

    @Override
    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    @Override
    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
