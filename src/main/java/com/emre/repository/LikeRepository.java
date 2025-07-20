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

    @Query(value = "select * from questapp.likes where post_id in :postIds limit 5",nativeQuery = true)
    List<Like> findUserLikesByPostId(@Param("postIds") List<Long> postIds);

}
