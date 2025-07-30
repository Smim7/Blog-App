package com.example.Blog_App.confiq;

import io.swagger.annotations.Info;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "BearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
//@OpenAPIDefinition(info = @Info(title = "Blog API", version = "1.0", description = "Blog App REST API"))
public class SwaggerConfig {
}
