package com.spring.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.SpringBootAppl
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//@SpringBootApplication(scanBasePackages={"com.spring.app.repository", "com.spring.app.controller","com.spring.app.pojo"})
//@SpringBootApplication(scanBasePackages = { "com.spring.app" })
//@EnableMongoRepositories("com.spring.app")
// @ComponentScan("com.spring.app.repository")
 @SpringBootApplication
public class SpringBootWebApplication  {

	// @Override
	// protected SpringApplicationBuilder configure(SpringApplicationBuilder
	// application) {
	// return application.sources(SpringBootWebApplication.class);
	// }

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}
}