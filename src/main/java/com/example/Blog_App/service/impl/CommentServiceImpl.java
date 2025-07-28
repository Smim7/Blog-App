package com.example.Blog_App.service.impl;

import com.example.Blog_App.entity.Comment;
import com.example.Blog_App.entity.Post;
import com.example.Blog_App.exception.BlogApiException;
import com.example.Blog_App.exception.ResourceNotFoundException;
import com.example.Blog_App.payLoad.CommentDto;
import com.example.Blog_App.repository.CommentRepository;
import com.example.Blog_App.repository.PostRepository;
import com.example.Blog_App.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;

        this.modelMapper = modelMapper;
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

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> comments=commentRepository.findByPostId(postId);
//convert list of comment entities of list of comment dto's
        return comments.stream()
                .map(comment->mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        //retrieve post entity by id
        Post post= postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        //retrieve comment by id
        Comment comment=commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("comment","id",commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong to this post");

        }
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {

        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        //retrieve comment by id
        Comment comment=commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("comment","id",commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong to this post");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());
        Comment updatedComment=commentRepository.save(comment);
        return mapToDto(updatedComment);

    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        Comment comment=commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("comment","id",commentId));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong to this post");
        }
         commentRepository.delete(comment);

    }


    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto=modelMapper.map(comment, CommentDto.class);
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }
    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment=modelMapper.map(commentDto, Comment.class);
//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }
}
