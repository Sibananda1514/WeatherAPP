package com.mindfire.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mindfire.dto.APIResponse;

/**
 * 
 * @author sibananda
 * 
 * Feign client component to communicate with Weather service
 *
 */
@FeignClient(name = "WEATHERSERVICE", url = "http://sibananda:8989/weather/")
public interface WeatherServiceClient {

	@GetMapping(path = "/getmaxmintemp")
	public ResponseEntity<APIResponse> getWeatherMaxMinBasedOnCity(@RequestParam(name = "location") String location);
}
