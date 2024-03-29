package com.mindfire.dto;

import java.util.List;

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
public class ForCastDay {

	private String date;
	private String date_epoch;
	private Day day;
	private Astro astro;
	private List<Hour> hour;
}
