package com.tommy.rideshare.driverManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DriverManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriverManagementApplication.class, args);
		System.out.print("Driver Management Starts:");
		for (int i = 0; i < 20; i++) {
			System.out.print("--------------------");
		}
		System.out.println();
	}

}
