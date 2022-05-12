package com.mindfire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WeatherDetailsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherDetailsApiApplication.class, args);
	}

}
