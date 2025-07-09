package com.emre.controller.impl;

import com.emre.controller.RootEntity;
import com.emre.controller.IRestUserController;
import com.emre.dto.DtoUser;
import com.emre.dto.DtoUserIU;
import com.emre.service.IUserService;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.emre.controller.RootEntity.ok;

@RestController
@RequestMapping("/users")
public class RestUserController implements IRestUserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    @Override
    public RootEntity<List<DtoUser>> getAllUsers() {
        return ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @Override
    public RootEntity<DtoUser> getUserById(@PathVariable Long id) {
        return ok(userService.getOneUserById(id));
    }

    @PostMapping("/save")
    @Override
    public RootEntity<DtoUser> saveUser(@RequestBody DtoUserIU dtoUserIU) {
        return ok(userService.saveUser(dtoUserIU));
    }

    @PutMapping("/{id}")
    @Override
    public RootEntity<DtoUser> updateUser(@PathVariable Long id, @RequestBody DtoUserIU dtoUserIU) {
        return ok(userService.updateUser(id, dtoUserIU));
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteUser(@PathVariable Long id) {
       userService.deleteUser(id);
    }




}
