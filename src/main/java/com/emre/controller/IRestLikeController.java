package com.emre.controller;

import com.emre.dto.DtoLikeIU;
import com.emre.dto.DtoLikeResponse;
import com.emre.entities.Like;

import java.util.List;
import java.util.Optional;

public interface IRestLikeController {

    public List<DtoLikeResponse> getAllLikes(Optional<Long> userId, Optional<Long> postId);
    public Like createOneLike( DtoLikeIU request);

    public Like getOneLike( Long likeId);

    public void deleteOneLike( Long likeId);
}
