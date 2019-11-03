package com.codegym.casestudybookmanagerment;

import com.codegym.casestudybookmanagerment.service.AuthorService;
import com.codegym.casestudybookmanagerment.service.ProductService;
import com.codegym.casestudybookmanagerment.service.impl.AuthorServiceImpl;
import com.codegym.casestudybookmanagerment.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

//import com.codegym.casestudybookmanagerment.service.UserDetailsServiceImpl;
//import com.codegym.casestudybookmanagerment.service.UserService;
//import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootApplication
public class CasestudyBookmanagermentApplication {

	@Autowired
    Environment env;

	public static void main(String[] args) {
		SpringApplication.run(CasestudyBookmanagermentApplication.class, args);
	}

	@Bean
	public ProductService productService() {
		return new ProductServiceImpl() {
		};
	}

	@Bean
	public AuthorService authorService() {
		return new AuthorServiceImpl() {
		};

	}

//	@Bean
//	public UserDetailsService userDetailsService(){
//		return new UserDetailsServiceImpl(){
//
//		};
//	}






}
