package com.crudjava;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CrudJava extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CrudJava.class);
	}

	public static void main(String[] args) {
		new CrudJava().configure(new SpringApplicationBuilder(CrudJava.class)).run(args);
	}
}
