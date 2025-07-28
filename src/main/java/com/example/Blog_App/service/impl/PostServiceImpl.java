package com.example.Blog_App.service.impl;

import com.example.Blog_App.entity.Post;
import com.example.Blog_App.payLoad.PostDto;
import com.example.Blog_App.repository.PostRepository;
import com.example.Blog_App.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl  implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public PostDto createPost(PostDto postDto) {
        //convert Dto to entity
        Post post = new Post();
        post.setTitle(post.getTitle());
        post.setDescription(post.getDescription());
        post.setContent(post.getContent());

        Post newPost=postRepository.save(post);

        //convert entity to Dto
        PostDto postResponse=new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());

        return postResponse;
    }
}
