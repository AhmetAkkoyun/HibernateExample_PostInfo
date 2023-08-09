package com.ahmetakkoyun.controller;

import com.ahmetakkoyun.repository.entity.Post;
import com.ahmetakkoyun.service.PostService;
import com.ahmetakkoyun.utility.ICrud;

import java.util.List;
import java.util.Optional;

public class PostController implements ICrud<Post> {

    private final PostService postService;

    public PostController() {
        this.postService = new PostService();
    }

    public Post save(Post post) {
        System.out.println("PostController çalışıyor...");
        return postService.save(post);
    }

    @Override
    public Post update(Post post) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }


}
