package com.example.Blog_App.Controller;

import com.example.Blog_App.payLoad.PostDto;
import com.example.Blog_App.payLoad.PostDto2;
import com.example.Blog_App.payLoad.PostResponse;
import com.example.Blog_App.service.PostService;
import com.example.Blog_App.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping()
//api/v1/posts
public class PostController {
    private PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }
    //create blog post REST API
    @PreAuthorize("hasRole('ADMIN')")
@PostMapping("api/v1/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {

     return new ResponseEntity<> (postService.createPost(postDto),HttpStatus.CREATED);
    }

    //get all posts REST API
@GetMapping("api/v1/posts")
 public PostResponse getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false)String sortDir
) {
        return postService.getALlPosts( pageNo, pageSize, sortBy,sortDir);

    }

    //get post by id
    //(value="/api/posts/{id}",headers = "X-API-VERSION=2")
@GetMapping(value="/api/posts/{id}",produces = "application/vnd.morshed.v2+json")
    public ResponseEntity<PostDto2> getPostByIdv2(@PathVariable Long id){
        PostDto postDto = postService.getPostById(id);
        PostDto2 postDto2 = new PostDto2();
        postDto2.setId(postDto.getId());
        postDto2.setTitle(postDto.getTitle());
        postDto2.setContent(postDto.getContent());
        postDto2.setDescription(postDto.getDescription());
        List<String> tags = new ArrayList<>();
        tags.add("java");
        tags.add("Spring boot");
        tags.add("AWS");
        postDto2.setTags(tags);

        return  ResponseEntity.ok(postDto2);
    }
//get post by id
    //(value="/api/posts/{id}",params = "version=1")
    @GetMapping(value="/api/posts/{id}",produces = "application/vnd.morshed.v1+json")
    public ResponseEntity<PostDto> getPostByIdv1(@PathVariable Long id){
        return  ResponseEntity.ok(postService.getPostById(id));
    }



    //update post by id REST API
    @PreAuthorize("hasRole('ADMIN')")
 @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,
                                              @PathVariable Long id) {
        PostDto updatedPost=postService.updatePost(postDto,id);
        return  new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    //Delete post REST API
@DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Long id) {
        postService.deletePostById(id);
        return  new ResponseEntity<> ("post deleted successfully",HttpStatus.OK);
    }

}
