package com.emre.repository;

import com.emre.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUserIdAndPostId(Long aLong, Long aLong1);

    List<Like> findByUserId(Long aLong);

    List<Like> findByPostId(Long aLong);

    // Defne any custom query methods if needed
    // For example, to find likes by user or post, you can add methods like:
    // List<Like> findByUserId(Long userId);
    // List<Like> findByPostId(Long postId);
}
