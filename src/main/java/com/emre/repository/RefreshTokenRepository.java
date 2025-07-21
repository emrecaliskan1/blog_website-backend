package com.emre.repository;

import com.emre.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {


    @Query("SELECT r FROM RefreshToken r WHERE r.user.id = :userId")
    RefreshToken findByUserId(@Param("userId") Long userId);
}
