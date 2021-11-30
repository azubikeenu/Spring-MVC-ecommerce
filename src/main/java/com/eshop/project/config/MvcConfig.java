package com.eshop.project.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path productsDir = Paths.get("product-images");
		String productImagesPath = productsDir.toFile().getAbsolutePath();
		registry.addResourceHandler("/" + productsDir + "/**").addResourceLocations("file:/" + productImagesPath + "/");
	}

}
