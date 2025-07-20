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
    public List<Post> findByUserId(Long userId);

    @EntityGraph(attributePaths = {"user"})
    public List<Post> findAll();

    @Query(value="select id from questapp.posts where user_id = :userId order by create_date desc limit 5 "
            ,nativeQuery = true)
    public List<Long> findTopByUserId(Long userId);

}
