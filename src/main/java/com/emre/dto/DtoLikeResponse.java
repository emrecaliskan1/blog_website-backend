package com.emre.dto;

import com.emre.entities.Like;
import lombok.Data;

@Data
public class DtoLikeResponse {

    private Long id;

    private Long userId;

    private Long postId;

    public DtoLikeResponse(Like entity){
        this.id=entity.getId();
        this.userId= entity.getUser().getId();
        this.postId= entity.getPost().getId();
    }

}
