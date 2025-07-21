package com.emre.service.impl;

import com.emre.entities.RefreshToken;
import com.emre.entities.User;
import com.emre.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Value("${refresh.token.expires.in}")
    Long expireSeconds;

    public boolean isRefreshTokenExpired(RefreshToken refreshToken) {
        return refreshToken.getExpiryDate().before(new Date());
    }

    public String createRefreshToken(User user){

        RefreshToken existing = refreshTokenRepository.findByUserId(user.getId());
        if (existing != null) {
            refreshTokenRepository.delete(existing);
        }

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

    public RefreshToken getByUserId(Long userId) {
        return refreshTokenRepository.findByUserId(userId);
    }
}
