package com.test.backend;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BackendTestApplication extends org.springframework.boot.web.servlet.support.SpringBootServletInitializer {

	static {
	
	}
	
	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(BackendTestApplication.class, args);

        System.out.println("List of all beans: ");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BackendTestApplication.class);
	}
	
}

