package com.project.springmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringmallApplication.class, args);
	}

}
