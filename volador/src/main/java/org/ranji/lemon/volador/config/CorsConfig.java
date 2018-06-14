package org.ranji.lemon.volador.config;

import org.springframework.context.annotation.Configuration;    
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;    
  
@Configuration    
public class CorsConfig extends WebMvcConfigurerAdapter {    
  
	
	//解决跨域问题
    @Override    
    public void addCorsMappings(CorsRegistry registry) {    
        registry.addMapping("/**")    
                .allowedOrigins("*")    
                .allowCredentials(true)    
                .allowedMethods("GET", "POST", "DELETE", "PUT")    
                .maxAge(3600);    
    }    
    
    //配置静态资源访问路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
    	/**
         * 如果我们将/xxxx/** 修改为 /** 与默认的相同时，则会覆盖系统的配置，可以多次使用 addResourceLocations 添加目录，
         * 优先级先添加的高于后添加的。
         *
         * 如果是/xxxx/** 引用静态资源 加不加/xxxx/ 均可，因为系统默认配置（/**）也会作用
         * 如果是/** 会覆盖默认配置，应用addResourceLocations添加所有会用到的静态资源地址，系统默认不会再起作用
         */
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
//        		.addResourceLocations("file:f:/feiyu/");
        registry.addResourceHandler("/we/**").addResourceLocations("file:D:/volador_home/data/Img/");
        super.addResourceHandlers(registry);
    }
  
}    