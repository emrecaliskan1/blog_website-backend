package com.emre.controller.impl;

import com.emre.controller.IRestPostController;
import com.emre.dto.DtoPostIU;
import com.emre.dto.DtoPostResponse;
import com.emre.dto.DtoPostUpdate;
import com.emre.entities.Post;
import com.emre.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class RestPostController implements IRestPostController {

    @Autowired
    private IPostService postService;

    @GetMapping
    @Override
    public List<DtoPostResponse> getAllPosts(@RequestParam Optional<Long> userId) {
        return postService.getAllPosts(userId);
    }

    @GetMapping("/{postId}")
    @Override
    public Post getOnePost(@PathVariable Long postId){
        return postService.getOnePostById(postId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody DtoPostIU dtoPostIU){
        return postService.createOnePost(dtoPostIU);
    }

    @PutMapping("/{postId}")
    @Override
    public Post updateOnePost(@PathVariable Long postId, @RequestBody DtoPostUpdate dtoPostUpdate){
        return postService.updateOnePost(postId, dtoPostUpdate);
    }

    @DeleteMapping("/{postId}")
    @Override
    public void deleteOnePost(@PathVariable Long postId) {
        postService.deleteOnePost(postId);
    }
}
