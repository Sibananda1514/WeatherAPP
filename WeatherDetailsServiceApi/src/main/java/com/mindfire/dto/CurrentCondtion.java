package com.mindfire.dto;

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
public class CurrentCondtion {

	private String last_updated_epoch;
	private String last_updated;
	private String temp_c;
	private Condtion condition;
	private String wind_mph;
	private String humidity;
	private AirQuality air_quality;
}
