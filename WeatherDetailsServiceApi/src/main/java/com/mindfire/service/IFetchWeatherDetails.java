package com.mindfire.service;

import com.mindfire.dto.MaxMinTempDto;
import com.mindfire.dto.WeatherResponseDTO;

public interface IFetchWeatherDetails {

	/**
	 * Fetch weather details based on City name
	 * 
	 * @param cityName contains the name of city details need to retrieve
	 * @return all city data in {@link WeatherResponseDTO}
	 */
	public WeatherResponseDTO fetchWeatherDetails(String cityName);

	/**
	 * Fetch max min temp for give city
	 * 
	 * @param cityName the name of city details need to retrieve
	 * @return max min tempt for given city wrapped in {@link MaxMinTempDto}
	 */
	public MaxMinTempDto fetchWeatherDetailsForMaxMin(String cityName);
}
