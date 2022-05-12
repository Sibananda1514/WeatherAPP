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
public class Hour {

	private String time_epoch;
	private String time;
	private String temp_c;
	private Integer is_day;
	private Condtion condition;
}
