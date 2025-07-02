package com.emre.service;

import com.emre.dto.DtoUser;
import com.emre.dto.DtoUserIU;

import java.util.List;

public interface IUserService {

    public List<DtoUser> getAllUsers();

    public DtoUser getUserById(Long id);

    public DtoUser saveUser(DtoUserIU dtoUserIU);

}
