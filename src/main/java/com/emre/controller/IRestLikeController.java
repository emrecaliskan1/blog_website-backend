package com.emre.controller;

import com.emre.dto.DtoLikeIU;
import com.emre.entities.Like;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface IRestLikeController {

    public List<DtoLikeIU> getAllLikes( Optional<Long> userId, Optional<Long> postId);
    public Like createOneLike( DtoLikeIU request);

    public Like getOneLike( Long likeId);

    public void deleteOneLike( Long likeId);
}
