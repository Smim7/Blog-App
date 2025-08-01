package com.example.Blog_App.Controller;

import com.example.Blog_App.payLoad.CommentDto;
import com.example.Blog_App.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId,
                                                   @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getAllCommentsByPostId(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long postId,
                                                     @PathVariable long commentId) {
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment (@PathVariable Long postId,
                                                    @PathVariable Long commentId,
                                                  @Valid @RequestBody CommentDto commentRequest) {
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentRequest),
                HttpStatus.OK);
    }
  @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String>deleteComment(@PathVariable Long postId,
                              @PathVariable Long commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment has been deleted", HttpStatus.OK);

    }
}

