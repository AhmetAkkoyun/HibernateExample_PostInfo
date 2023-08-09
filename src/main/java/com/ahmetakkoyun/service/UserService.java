package com.ahmetakkoyun.service;

import com.ahmetakkoyun.repository.UserRepository;
import com.ahmetakkoyun.repository.entity.User;
import com.ahmetakkoyun.utility.ICrud;

import java.util.List;
import java.util.Optional;

public class UserService implements ICrud<User> {

    private final UserRepository userRepository;

    public UserService(){
        System.out.println("UserService çalışıyor...");
        this.userRepository = new UserRepository();
    }

    public User save(User user) {
        return userRepository.save(user);
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
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            System.out.println("VERİTABANINDA "+username+" KULLANICISI BULUNAMADI!!!!");
        }
        return user;
    }
}
