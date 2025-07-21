package com.emre.dto;

import com.emre.entities.Comment;
import lombok.Data;

@Data
public class CommentResponse {

    Long id;
    Long userId;
    String username;
    String text;

    public CommentResponse(Comment entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.text = entity.getText();
        this.username=entity.getUser().getUsername();
    }
}
