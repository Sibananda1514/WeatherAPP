package com.mindfire.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.dto.APIResponse;
import com.mindfire.dto.MaxMinTempDto;
import com.mindfire.dto.WeatherResponseDTO;
import com.mindfire.service.IFetchWeatherDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(path = "/weather")
@Api(value = "weather")
public class WeatherController {

	@Autowired
	private IFetchWeatherDetails fwdSer;

	@ApiOperation(value = "Fetch weather info based on input location", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Return gatherd weather data"),
			@ApiResponse(code = 404, message = "Unable to fetch weather data") })

	@GetMapping(path = "")
	public ResponseEntity<?> getWeatherData(@RequestParam("location") String location) {
		WeatherResponseDTO fetchWeatherDetailsResp = fwdSer.fetchWeatherDetails(location);
		
		if (fetchWeatherDetailsResp != null)

			return ResponseEntity.ok(fetchWeatherDetailsResp);
		else

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "/getmaxmintemp", notes = "Fetch max min temp info based on input location", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Return gatherd weather data"),
			@ApiResponse(code = 404, message = "Unable to fetch weather data") })

	@GetMapping(path = "/getmaxmintemp")
	public ResponseEntity<APIResponse> getWeatherMaxMinBasedOnCity(@RequestParam("location") String location) {
		MaxMinTempDto fetchWeatherDetailsForMaxMin = fwdSer.fetchWeatherDetailsForMaxMin(location);
		APIResponse resp = new APIResponse();

		if (fetchWeatherDetailsForMaxMin != null) {
			Map<String, Object> addtionaldata = new HashMap<>();
			resp.setMessage("Data Retrived");
			resp.setAddtionalinfo(addtionaldata);
			addtionaldata.put("maxmincitydata", fetchWeatherDetailsForMaxMin);

			return ResponseEntity.ok(resp);

		} else {
			resp.setMessage("No Data Retrived");

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
