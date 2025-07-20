package com.emre.repository;

import com.emre.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByUserIdAndPostId(Long userId, Long postId);

    List<Comment> findByUserId(Long userId);

    List<Comment> findByPostId(Long postId);

    @Query(value = "select * from questapp.comments where post_id in :postIds limit 5",nativeQuery = true)
    List<Comment> findUserCommentsByPostId(@Param("postIds") List<Long> postIds);

}
