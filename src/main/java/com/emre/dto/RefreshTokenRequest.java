package com.emre.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequest {

    Long userId;

    String refreshToken;

}
