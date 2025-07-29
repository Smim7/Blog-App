package com.example.Blog_App.repository;


import com.example.Blog_App.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<com.example.Blog_App.entity.Role, Long> {
    Optional<Role> findByName(String name);
}
