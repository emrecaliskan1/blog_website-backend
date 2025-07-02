package com.emre.controller;

import com.emre.dto.DtoUser;
import com.emre.dto.DtoUserIU;

import java.util.List;

public interface IRestUserController {

    public RootEntity<List<DtoUser>> getAllUsers();

    public RootEntity<DtoUser> getUserById(Long id);

    public RootEntity<DtoUser> saveUser(DtoUserIU dtoUserIU);
}
