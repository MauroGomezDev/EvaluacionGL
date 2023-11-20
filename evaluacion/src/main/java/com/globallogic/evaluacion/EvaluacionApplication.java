package com.globallogic.evaluacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@ComponentScan(basePackages = "com.globallogic.evaluacion")
public class EvaluacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaluacionApplication.class, args);
	}

}
