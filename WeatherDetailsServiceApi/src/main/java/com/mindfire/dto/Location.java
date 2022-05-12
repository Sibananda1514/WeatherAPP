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
public class Location {

	private String name;
	private String region;
	private String country;
	private String localtime;
	private String localtime_epoch;
	private String tz_id;
	
}
