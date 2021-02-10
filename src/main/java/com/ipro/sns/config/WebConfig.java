package com.ipro.sns.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    protected static final String loImgPath = "/Users/yoon sung/Desktop/upload/";
    protected static final String ubImgPath = "/home/ubuntu/apps/upload/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/upload/**")
                .addResourceLocations("file://" + ubImgPath)
                .setCachePeriod(20);
    }
}
