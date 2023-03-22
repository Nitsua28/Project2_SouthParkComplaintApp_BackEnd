package com.revature.Project2_SouthParkComplaintApp_BackEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:prod.properties")
public class Project2SouthParkComplaintAppBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project2SouthParkComplaintAppBackEndApplication.class, args);
	}

}
