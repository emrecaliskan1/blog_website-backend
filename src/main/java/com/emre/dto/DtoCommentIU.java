package com.emre.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCommentIU {

    Long userId;

    Long postId;

    String text;
}
