package com.emre.controller;

import com.emre.dto.DtoPostIU;
import com.emre.dto.DtoPostResponse;
import com.emre.dto.DtoPostUpdate;
import com.emre.entities.Post;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface IRestPostController {

    public List<DtoPostResponse> getAllPosts(Optional<Long> userId);

    public Post getOnePost(Long postId);
    public Post createOnePost( DtoPostIU dtoPostIU);

    public Post updateOnePost( Long postId,  DtoPostUpdate dtoPostUpdate);

    public void deleteOnePost( Long postId);
}
