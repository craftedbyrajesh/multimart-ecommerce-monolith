package com.app.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class EcomApplication {

	public static void main(String[] args) {

		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));

		System.out.println("JVM TimeZone = " + TimeZone.getDefault().getID());

		SpringApplication.run(EcomApplication.class, args);
	}

}
