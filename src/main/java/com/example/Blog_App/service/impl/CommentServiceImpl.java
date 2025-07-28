package com.example.Blog_App.service.impl;

import com.example.Blog_App.entity.Comment;
import com.example.Blog_App.entity.Post;
import com.example.Blog_App.exception.ResourceNotFoundException;
import com.example.Blog_App.payLoad.CommentDto;
import com.example.Blog_App.repository.CommentRepository;
import com.example.Blog_App.repository.PostRepository;
import com.example.Blog_App.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;

    }


    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment=mapToEntity(commentDto);
     Post post= postRepository.findById(postId)
             .orElseThrow(()->new ResourceNotFoundException("post","id",postId));

     //set post to comment
     comment.setPost(post);

     Comment newComment=commentRepository.save(comment);

        return mapToDto(newComment);
    }



    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }
    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}
