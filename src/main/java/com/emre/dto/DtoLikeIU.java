package com.emre.dto;

import com.emre.entities.Like;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoLikeIU {

    //private Long id;

    private Long userId;

    private Long postId;

    public DtoLikeIU(Like entity) {
       // this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.postId = entity.getPost().getId();
    }

}
