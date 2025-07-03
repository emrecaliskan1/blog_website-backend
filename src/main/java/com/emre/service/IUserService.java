package com.emre.service;

import com.emre.dto.DtoUser;
import com.emre.dto.DtoUserIU;

import java.util.List;

public interface IUserService {

    public List<DtoUser> getAllUsers();

    public DtoUser getOneUserById(Long id);

    public DtoUser saveUser(DtoUserIU dtoUserIU);

    public DtoUser updateUser(Long id, DtoUserIU dtoUserIU);

    public void deleteUser(Long id);
}
