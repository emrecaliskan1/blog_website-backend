package com.emre.repository;

import com.emre.entities.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @EntityGraph(attributePaths = {"user"})
    List<Post> findByUserId(Long userId);

    @EntityGraph(attributePaths = {"user"})
    List<Post> findAll();

    @Query(value = "SELECT id FROM questapp.posts WHERE user_id = ?1 ORDER BY created_at DESC LIMIT 5", nativeQuery = true)
    List<Long> findTopByUserId(Long userId);

    List<Post> findByIdIn(List<Long> postIds);
}
