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
public class MaxMinTempDto {

	
	private Integer id;
	private String maxTemp;
	private String minTemp;
	private String cityName;
	
	
}
