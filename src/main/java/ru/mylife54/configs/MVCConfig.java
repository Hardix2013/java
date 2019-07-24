package ru.mylife54.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/img/**").addResourceLocations(System.getProperty("user.dir")+"/imgs/");
        registry.addResourceHandler("/img/**").addResourceLocations("file://"+uploadPath+"/");
        registry.addResourceHandler("/webjars").addResourceLocations("/webjars/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }
}