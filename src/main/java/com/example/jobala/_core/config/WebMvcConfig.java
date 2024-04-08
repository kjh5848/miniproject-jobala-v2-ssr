package com.example.jobala._core.config;

import com.example.jobala._core.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // 로그인 인터셉터
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/guest/**","/comp/**","/applyForm/","/positionForm/","/board/**","/reply/**")
                .excludePathPatterns("/board/mainForm","/board/{\\d+}","/guest/jobSearch","/comp/jobopen/{\\d+}" ,"/guest/jobopenSearch");
    }

    //외부이미지 경로설정
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:./image/")
                .setCachePeriod(60 * 60) // 초 단위 => 한시간
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

}