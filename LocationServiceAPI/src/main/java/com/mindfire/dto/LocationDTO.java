package com.mindfire.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {

	private Integer id;

	private String locationName;

	private String isActive;

	private Integer position;

}
