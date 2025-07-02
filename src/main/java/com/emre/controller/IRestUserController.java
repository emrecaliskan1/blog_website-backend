package com.emre.controller;

import com.emre.dto.DtoUser;
import com.emre.dto.DtoUserIU;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IRestUserController {

    public RootEntity<List<DtoUser>> getAllUsers();

    public RootEntity<DtoUser> getUserById(Long id);

    public RootEntity<DtoUser> saveUser(DtoUserIU dtoUserIU);

    public RootEntity<DtoUser> updateUser( Long id,  DtoUserIU dtoUserIU);

    public void deleteUser( Long id);

}
