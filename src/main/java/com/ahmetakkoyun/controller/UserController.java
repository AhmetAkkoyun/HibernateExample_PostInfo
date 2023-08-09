package com.ahmetakkoyun.controller;

import com.ahmetakkoyun.repository.UserRepository;
import com.ahmetakkoyun.repository.entity.User;
import com.ahmetakkoyun.service.UserService;
import com.ahmetakkoyun.utility.HibernateUtility;
import com.ahmetakkoyun.utility.ICrud;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class UserController implements ICrud<User> {

    private final UserService userService;

    public UserController() {
        System.out.println("UserController çalışıyor...");
        this.userService = new UserService();
    }

    public User save(User user) {
        return userService.save(user);
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public List<User> findAll() {
        return userService.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userService.findById(id);
    }

    public Optional<User> findByUsername(String username){
        return userService.findByUsername(username);
    }


}

