package com.mindfire.constants;

/**
 * Contains end point constant info
 * 
 * @author sibananda
 *
 */
public class EndPoints {

	private EndPoints() {

	}

	public static final String WEATHER_END_POINT = "http://api.weatherapi.com/v1/forecast.json?key=483a04daff914ce58f991903220803&q=${city}&days=7&aqi=yes&alerts=no";
}
