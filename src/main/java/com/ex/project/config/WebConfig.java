package com.ex.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private String resourcesPath ="/upload/**"; //html에서 사용할 경로지정
    private String savePath ="file:///D:/dog_project/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(resourcesPath)
                .addResourceLocations(savePath);
    }
}
