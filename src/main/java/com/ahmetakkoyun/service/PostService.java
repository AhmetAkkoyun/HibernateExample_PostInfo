package com.ahmetakkoyun.service;

import com.ahmetakkoyun.repository.PostRepository;
import com.ahmetakkoyun.repository.entity.Post;
import com.ahmetakkoyun.utility.ICrud;

import java.util.List;
import java.util.Optional;

public class PostService implements ICrud<Post> {

    private final PostRepository postRepository;

    public PostService() {
        this.postRepository = new PostRepository();
    }

    public Post save(Post post) {
        System.out.println("PostService -> PostRepository");
        return postRepository.save(post);
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
