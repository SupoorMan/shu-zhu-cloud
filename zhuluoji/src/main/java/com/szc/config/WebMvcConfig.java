package com.szc.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;


@SpringBootConfiguration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor getHandlerInterceptor() {
        return new HttpHandlerInterceptor();
    }

    /**
     * 设置拦截规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/wx/**")
                .excludePathPatterns("/file/**")
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/swagger-ui/index.html")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/v2/**")
                .excludePathPatterns("/v3/**")
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("/swagger-ui.html/**")
        ;
    }

    /**
     * 设置跨域规则
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//设置允许跨域的路径
                //设置允许跨域请求的域名 allowedOriginPatterns
                .allowedOriginPatterns("*")
                .allowedOrigins("Access-Control-Allow-Origin")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "OPTIONS", "DELETE", "PUT")//设置允许的方法
                .allowedHeaders("*")
                .exposedHeaders("*")
                .maxAge(36000);//跨域允许时间,秒
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**", "/swagger-ui/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/",
                        "swagger-ui/index.html",
                        "classpath:/META-INF/resources/webjars/springfox-swagger-ui/",
                        "doc.html")
                .resourceChain(false);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui/")
                .setViewName("forward:/swagger-ui/index.html");
    }

}
