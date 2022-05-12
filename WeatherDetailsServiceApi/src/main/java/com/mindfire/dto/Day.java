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
public class Day {

	private String maxtemp_c;
	private String mintemp_c;
	private Double daily_chance_of_rain;
	private Condtion condition;
	private String uv;
	
}
