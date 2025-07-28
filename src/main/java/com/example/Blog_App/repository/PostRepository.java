package com.example.Blog_App.repository;

import com.example.Blog_App.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository  extends JpaRepository<Post,Long> {

}
