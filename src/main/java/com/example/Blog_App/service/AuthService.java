package com.example.Blog_App.service;

import com.example.Blog_App.payLoad.LoginDto;
import com.example.Blog_App.payLoad.RegisterDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
