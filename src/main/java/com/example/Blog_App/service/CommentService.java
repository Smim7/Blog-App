package com.example.Blog_App.service;

import com.example.Blog_App.payLoad.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(Long postId);

    CommentDto getCommentById(Long postId,Long commentId);

    CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest);

    void deleteComment(Long postId, Long commentId);
}
