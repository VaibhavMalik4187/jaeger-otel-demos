package com.demo.java_agent;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaAgentApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(JavaAgentApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}
