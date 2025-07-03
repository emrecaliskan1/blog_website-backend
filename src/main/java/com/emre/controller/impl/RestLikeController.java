package com.emre.controller.impl;

import com.emre.dto.DtoLikeIU;
import com.emre.entities.Like;
import com.emre.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class RestLikeController implements com.emre.controller.IRestLikeController {

    @Autowired
    private ILikeService likeService;

    @GetMapping
    @Override
    public List<DtoLikeIU> getAllLikes(@RequestParam Optional<Long> userId,
                                       @RequestParam Optional<Long> postId) {
        return likeService.getAllLikesWithParam(userId, postId);
    }

    @PostMapping
    @Override
    public Like createOneLike(@RequestBody DtoLikeIU request) {
        return likeService.createOneLike(request);
    }

    @GetMapping("/{likeId}")
    @Override
    public Like getOneLike(@PathVariable Long likeId) {
        return likeService.getOneLikeById(likeId);
    }

    @DeleteMapping("/{likeId}")
    @Override
    public void deleteOneLike(@PathVariable Long likeId) {
        likeService.deleteOneLikeById(likeId);
    }

}
