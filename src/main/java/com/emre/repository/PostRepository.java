package com.emre.repository;

import com.emre.dto.DtoPostResponse;
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

    @Query(value = "SELECT id FROM questapp.posts WHERE user_id = ?1 ORDER BY create_date DESC LIMIT 5", nativeQuery = true)
    //@Query(value = "select 'posted', p.title, p.text, p.create_date from questapp.posts p where p.user_id = :userId order by p.create_date desc limit 5",nativeQuery = true)
    public List<Long> findTopByUserId(Long userId);

    List<Post> findByIdIn(List<Long> postIds);
}
