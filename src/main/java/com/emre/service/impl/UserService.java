package com.emre.service.impl;

import com.emre.dto.DtoLikeResponse;
import com.emre.dto.DtoUser;
import com.emre.dto.DtoUserIU;
import com.emre.entities.Comment;
import com.emre.entities.Like;
import com.emre.entities.Post;
import com.emre.entities.User;
import com.emre.repository.CommentRepository;
import com.emre.repository.LikeRepository;
import com.emre.repository.PostRepository;
import com.emre.repository.UserRepository;
import com.emre.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    private User createUser(DtoUserIU dtoUserIU) {
        User user = new User();
        BeanUtils.copyProperties(dtoUserIU, user);
        return user;
    }

    @Override
    public List<DtoUser> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<DtoUser> dtoList = new ArrayList<>();
        for (User user : users) {
            DtoUser dto = new DtoUser();
            BeanUtils.copyProperties(user, dto);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public DtoUser getOneUserById(Long id) {
        DtoUser dtoUser = new DtoUser();
        Optional<User> optUser = userRepository.findById(id);
        if(optUser.isEmpty()){
            return null;
        }
        User user = optUser.get();
        BeanUtils.copyProperties(user, dtoUser);
        return dtoUser;
    }

    @Override
    public DtoUser getOneUserByUsername(String username) {
        DtoUser dtoUser = new DtoUser();
        User user = userRepository.findByUsername(username);
        if(user == null){
            return null;
        }
        BeanUtils.copyProperties(user, dtoUser);
        return dtoUser;
    }

    @Override
    public DtoUser saveUser(DtoUserIU dtoUserIU) {
        DtoUser dtoUser = new DtoUser();
        User savedUser = userRepository.save(createUser(dtoUserIU));
        BeanUtils.copyProperties(savedUser, dtoUser);
        return dtoUser;
    }

    @Override
    public DtoUser updateUser(Long id, DtoUserIU dtoUserIU) {
        Optional<User> optUser = userRepository.findById(id);
        DtoUser dtoUser = new DtoUser();
        if(optUser.isPresent()){
            User foundUser = optUser.get();
            foundUser.setUsername(dtoUserIU.getUsername());
            foundUser.setPassword(dtoUserIU.getPassword());
            userRepository.save(foundUser);
            BeanUtils.copyProperties(foundUser, dtoUser);
            return dtoUser;
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserEntityById(Long userId) {
        return userRepository.findById(userId)
                .orElse(null);
    }

    @Override
    public List<Object> getUserActivity(Long userId) {
        List<Long> postIds = postRepository.findTopByUserId(userId);
        if(postIds.isEmpty()){
            return null;
        }

        List<Post> posts = postRepository.findByIdIn(postIds);
        List<Object> comments = commentRepository.findUserCommentsByPostId(postIds);
        List<Object> likes = likeRepository.findUserLikesByPostId(postIds);
        //List<DtoLikeResponse> likeDtos = likes.stream()
        //        .map(DtoLikeResponse::new)
        //        .collect(Collectors.toList());
        //System.out.println(likes);
        //System.out.println(likeDtos);
        List<Object> result = new ArrayList<>();
        result.addAll(posts);
        result.addAll(comments);
        result.addAll(likes);
        return result;

    }

}
