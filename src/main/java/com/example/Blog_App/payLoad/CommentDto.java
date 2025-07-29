package com.example.Blog_App.payLoad;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;

    //name should not be null or empty
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    //email should not be null
    //email field validation
    @NotEmpty
    @Email
    private String email;

    //comment body should not be null or empty
    //comment body must be minimum 10 characters
    @NotEmpty
    @Size(min=10,message = "comment body must be minimum 10 characters")
    private String body;
}
