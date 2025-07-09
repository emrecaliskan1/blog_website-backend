package com.emre.repository;

import com.emre.entities.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @EntityGraph(attributePaths = {"user"})
    public List<Post> findByUserId(Long userId);

    @EntityGraph(attributePaths = {"user"})
    public List<Post> findAll();

}
