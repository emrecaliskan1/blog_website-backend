package com.emre.dto;

import com.emre.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoPostResponse {

    Long id;

    Long userId;

    String username;

    String title;

    String text;

    List<DtoLikeResponse> postLikes;

    Date createdAt;


    public DtoPostResponse(Post entity, List<DtoLikeResponse> likes){
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.username = entity.getUser().getUsername();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.postLikes = likes;
        this.createdAt = entity.getCreatedAt();
    }



}
