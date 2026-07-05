package com.food_order.backend.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProjectConfig {

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public Cloudinary getCloudinary() {
        Map<String, Object> config = new HashMap<>();
        config.put("cloud_name", "fjw6l3hc");
        config.put("api_key", "214358145745596");
        config.put("api_secret", "5boA7dF98ubhxVNlkVywIV7A7UM");
        config.put("secure", true);
        return new Cloudinary(config);
    }

}
