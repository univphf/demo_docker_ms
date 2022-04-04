package com.insa.fr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"com.insa.fr"})
public class service_logs {

	public static void main(String[] args) {
		SpringApplication.run(service_logs.class, args);
	}

}
