package com.example.Blog_App.Controller;

import com.example.Blog_App.payLoad.PostDto;
import com.example.Blog_App.payLoad.PostResponse;
import com.example.Blog_App.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }
    //create blog post REST API
@PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {

     return new ResponseEntity<> (postService.createPost(postDto),HttpStatus.CREATED);
    }

    //get all posts REST API
@GetMapping
 public PostResponse getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(value="pageSize",defaultValue = "10",required = false) int pageSize
) {
        return postService.getALlPosts( pageNo, pageSize);

    }

    //get post by id
@GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
        return  ResponseEntity.ok(postService.getPostById(id));
    }

    //update post by id REST API
 @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                              @PathVariable Long id) {
        PostDto updatedPost=postService.updatePost(postDto,id);
        return  new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    //Delete post REST API
@DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Long id) {
        postService.deletePostById(id);
        return  new ResponseEntity<> ("post deleted successfully",HttpStatus.OK);
    }

}
