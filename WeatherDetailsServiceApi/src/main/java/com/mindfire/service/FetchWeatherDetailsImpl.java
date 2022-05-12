package com.mindfire.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mindfire.constants.EndPoints;
import com.mindfire.dto.Day;
import com.mindfire.dto.ForCastDay;
import com.mindfire.dto.Hour;
import com.mindfire.dto.MaxMinTempDto;
import com.mindfire.dto.WeatherResponseDTO;

@Service
public class FetchWeatherDetailsImpl implements IFetchWeatherDetails {

	private static final Logger logger = LogManager.getLogger(FetchWeatherDetailsImpl.class);
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public WeatherResponseDTO fetchWeatherDetails(String cityName) {
		logger.info("Fetching weather detailas for city {}", cityName);

		ResponseEntity<WeatherResponseDTO> responseEntity;
		String url = EndPoints.WEATHER_END_POINT.replace("${city}", cityName);
		HttpEntity<?> entity = new HttpEntity<>(null);
		WeatherResponseDTO weatherResponse;

		responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, WeatherResponseDTO.class);
		weatherResponse = responseEntity.getBody();
		if (weatherResponse != null) {
			weatherResponse.setAddtionalInfo(new HashMap<>());
			prepareNextHourResp(weatherResponse);
		}

		return weatherResponse;
	}

	/**
	 * Prepare next few hour weather condition data
	 * 
	 * @param weatherResponse contains next few hour weather condition data
	 */
	private void prepareNextHourResp(WeatherResponseDTO weatherResponse) {

		LocalDateTime fetchTime = Instant
				.ofEpochMilli(Long.parseLong(weatherResponse.getLocation().getLocaltime_epoch()))
				.atZone(ZoneId.systemDefault()).toLocalDateTime();

		List<ForCastDay> upComingForeCast = weatherResponse.getForecast().getForecastday();

		List<Hour> next7HrForeCastData = upComingForeCast.stream()
				.flatMap(forecastday -> forecastday.getHour().stream()).filter(hour -> {

					LocalDateTime hourDate = Instant.ofEpochMilli(Long.parseLong(hour.getTime_epoch()))
							.atZone(ZoneId.systemDefault()).toLocalDateTime();

					return fetchTime.isBefore(hourDate);

				}).limit(9).collect(Collectors.toList());

		logger.info("Data update time {}", fetchTime);
		logger.info("next 7 hour data {}", next7HrForeCastData);

		weatherResponse.getAddtionalInfo().put("nextHrData", next7HrForeCastData);
	}

	@Override
	public MaxMinTempDto fetchWeatherDetailsForMaxMin(String cityName) {
		logger.info("City name for max min temp {}", cityName);

		WeatherResponseDTO fetchWeatherDetails = fetchWeatherDetails(cityName);
		Day currenntDayData = fetchWeatherDetails.getForecast().getForecastday().get(0).getDay();
		MaxMinTempDto maxMinTempDto = new MaxMinTempDto();

		logger.info("City name for max min temp Resp fo {}", fetchWeatherDetails);

		maxMinTempDto.setCityName(fetchWeatherDetails.getLocation().getName());
		maxMinTempDto.setMaxTemp(currenntDayData.getMaxtemp_c());
		maxMinTempDto.setMinTemp(currenntDayData.getMintemp_c());

		return maxMinTempDto;
	}
}
