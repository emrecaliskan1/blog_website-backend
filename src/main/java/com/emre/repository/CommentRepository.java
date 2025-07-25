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

    //@Query(value = "SELECT * FROM questapp.comments WHERE post_id IN (:postIds) LIMIT 5", nativeQuery = true)
    @Query(value = "select 'commented on', c.post_id , u.username from "
    + "questapp.comments c left join questapp.users u on u.id = c.user_id "
    + "where c.post_id in :postIds limit 5", nativeQuery = true)
    List<Object> findUserCommentsByPostId(@Param("postIds") List<Long> postIds);

}
