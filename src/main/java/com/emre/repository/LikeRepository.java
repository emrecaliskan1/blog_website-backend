package com.emre.repository;

import com.emre.entities.Comment;
import com.emre.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUserIdAndPostId(Long aLong, Long aLong1);

    List<Like> findByUserId(Long aLong);

    List<Like> findByPostId(Long aLong);

    //@Query(value = "SELECT * FROM questapp.likes WHERE post_id IN (:postIds)", nativeQuery = true)
    @Query(value = "select 'liked', l.post_id, u.username from "
    + "questapp.likes l left join questapp.users u on u.id = l.user_id "
    + "where l.post_id in :postIds limit 5", nativeQuery = true)
    List<Object> findUserLikesByPostId(@Param("postIds") List<Long> postIds);

}
