package com.emre.service;

import com.emre.dto.DtoPostIU;
import com.emre.dto.DtoPostResponse;
import com.emre.dto.DtoPostUpdate;
import com.emre.entities.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {

    List<DtoPostResponse> getAllPosts(Optional<Long> userId);

    Post getOnePostById(Long postId);

    Post createOnePost(DtoPostIU dtoPostIU);

    Post updateOnePost(Long postId, DtoPostUpdate dtoPostUpdate);

    void deleteOnePost(Long postId);
}
