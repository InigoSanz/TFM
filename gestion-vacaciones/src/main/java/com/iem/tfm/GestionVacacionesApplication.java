package com.iem.tfm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class GestionVacacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionVacacionesApplication.class, args);
	}

}
