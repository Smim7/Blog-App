package com.example.Blog_App.service;

import com.example.Blog_App.payLoad.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getALlPosts();
    PostDto getPostById(Long id);
    PostDto updatePost(PostDto postDto,Long id);
    void deletePostById(Long id);
}
