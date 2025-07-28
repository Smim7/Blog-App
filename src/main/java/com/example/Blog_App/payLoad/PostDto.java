package com.example.Blog_App.payLoad;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    //title should not be null or empty
    //title should have at least 2 characters
    @NotEmpty
    @Size(min=2,message = "post title should have at least 2 characters")
    private String title;

    //post description should not null or empty
    //post description should have at least 10 characters
    @NotEmpty
    @Size(min=10,message ="post description should have at least 10 description" )
    private String description;

    //post content should not be null,empty
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;

}
