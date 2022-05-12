package com.mindfire.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WeatherResponseDTO {

	private Location location;
	private CurrentCondtion current;
	private ForeCast forecast;
	private Map<String,Object> addtionalInfo;
}
