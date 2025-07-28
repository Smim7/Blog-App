package com.example.Blog_App.service;

import com.example.Blog_App.payLoad.CommentDto;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto commentDto);
}
