package com.nagarro.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@EnableWebMvc
@Configuration
@ComponentScan({"com.nagarro.controllers"})
public class ServletConfig extends WebMvcConfigurerAdapter{
//public class ServletConfig{
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver internalView = new InternalResourceViewResolver();
		internalView.setSuffix(".jsp");
		return internalView;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
        .addResourceLocations("/resources/");
    }
}
