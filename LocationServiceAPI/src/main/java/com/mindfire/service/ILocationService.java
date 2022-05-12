package com.mindfire.service;

import java.util.List;

import com.mindfire.dto.LocationRequest;
import com.mindfire.dto.MaxMinTempDto;

public interface ILocationService {

	/**
	 * Creating new location
	 * 
	 * @param req contains location to create {@link LocationRequest}
	 * 
	 * @return {@link Boolean}
	 */
	public boolean createLocation(LocationRequest req);

	/**
	 * Delete location
	 * 
	 * @param locationId contains id of location to delete
	 * 
	 * @return {@link Boolean}
	 */
	public boolean deleteLocation(Integer locationId);

	/**
	 * Collect all saved locations
	 * 
	 * @return
	 */
	public List<MaxMinTempDto> fetchAllLocation();

	/**
	 * Update position of a particular Location
	 * 
	 * @param prevIndex    contains index position of location before update
	 * @param currentIndex contains current index position to which location
	 *                     position want to change
	 * @return {@link Boolean}
	 */
	public boolean updateLocation(int prevIndex, int currentIndex);
}
