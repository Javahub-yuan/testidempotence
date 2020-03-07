package com.juneyaoair.config;

import com.juneyaoair.interceptor.ApiIdempotentInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(apiIdempotentInterceptor());
        registration.addPathPatterns("/**");    //所有路径都被拦截
        registration.excludePathPatterns("/");  //添加不拦截路径
    }

    /**
     * 拦截器加载的时间点在springcontext之前，所以在拦截器中注入为null，
     * 所以需要在配置文件中使用@Bean注解提前去加载
     * @return
     */
    @Bean
    public ApiIdempotentInterceptor apiIdempotentInterceptor() {
        return new ApiIdempotentInterceptor();
    }
}
