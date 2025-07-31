package com.example.Blog_App;

import com.example.Blog_App.entity.Role;
import com.example.Blog_App.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApplication  implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		Role adminRole =new Role();
		adminRole.setName("ROLE_ADMIN");
		Role userRole =new Role();
		roleRepository.save(adminRole);

		Role userRole2 =new Role();
		userRole2.setName("ROLE_USER");
		roleRepository.save(userRole2);

	}

}
