package com.skeleton.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry register){
        register.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "PUT", "POST", "DELETE", "OPTION")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(1800);
    }

    public void addInterceptors(InterceptorRegistry register){
        register.addInterceptor(new Interceptor())
                .addPathPatterns("/**");
//                .excludePathPatterns("")
    }

}
