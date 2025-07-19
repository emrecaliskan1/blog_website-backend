package com.emre.service;

import com.emre.dto.DtoLikeIU;
import com.emre.dto.DtoLikeResponse;
import com.emre.entities.Like;

import java.util.List;
import java.util.Optional;

public interface ILikeService {


    List<DtoLikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId);

    Like createOneLike(DtoLikeIU request);

    Like getOneLikeById(Long likeId);

    void deleteOneLikeById(Long likeId);
}

