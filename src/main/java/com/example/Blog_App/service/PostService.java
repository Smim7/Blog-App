package com.example.Blog_App.service;

import com.example.Blog_App.payLoad.PostDto;
import com.example.Blog_App.payLoad.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getALlPosts(int PageNo, int pageSize);
    PostDto getPostById(Long id);
    PostDto updatePost(PostDto postDto,Long id);
    void deletePostById(Long id);
}
