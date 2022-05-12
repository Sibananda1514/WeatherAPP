package com.mindfire.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindfire.client.WeatherServiceClient;
import com.mindfire.dto.APIResponse;
import com.mindfire.dto.LocationDTO;
import com.mindfire.dto.LocationRequest;
import com.mindfire.dto.MaxMinTempDto;
import com.mindfire.entities.LocationEnitity;
import com.mindfire.exceptions.LocationNotFound;
import com.mindfire.repo.LocationRepo;

@Service
public class LocationSerImpl implements ILocationService {

	@Autowired
	private LocationRepo locationRepositry;

	@Autowired
	private WeatherServiceClient weatherClient;

	private static final Logger logger = LogManager.getLogger(LocationSerImpl.class);

	/**
	 * 
	 */
	@Override
	public boolean deleteLocation(Integer locationId) {
		logger.info("Softdeleteing location for {}", locationRepositry);

		locationRepositry.findById(locationId).ifPresentOrElse(ent -> {
			ent.setIsActive("N");
			locationRepositry.save(ent);
		}, () -> {
			throw new LocationNotFound("Location Not Exist for Delete...");
		});

		return true;
	}

	@Override
	public List<MaxMinTempDto> fetchAllLocation() {
		List<MaxMinTempDto> listLoc = new ArrayList<>();

		locationRepositry.findByisActive("Y").stream().sorted(Comparator.comparing(LocationEnitity::getPosition))
				.forEach(avlblLocEnt -> {
					logger.info("City Name for max min temp {}", avlblLocEnt.getLocationName());

					ResponseEntity<APIResponse> weatherMaxMinBasedOnCity = weatherClient
							.getWeatherMaxMinBasedOnCity(avlblLocEnt.getLocationName());

					if (weatherMaxMinBasedOnCity.getStatusCode() == HttpStatus.OK) {

						MaxMinTempDto dto = new ObjectMapper().convertValue(
								weatherMaxMinBasedOnCity.getBody().getAddtionalinfo().get("maxmincitydata"),
								MaxMinTempDto.class);
						dto.setId(avlblLocEnt.getId());

						listLoc.add(dto);
					}
				});

		logger.info("Max Min Temp for all City {}", listLoc);

		return listLoc;
	}

	@Override
	public boolean updateLocation(int prevIndex, int currentIndex) {
		logger.info("Location update request with prevIndex {} ", prevIndex);
		logger.info("Location update request with currentIndex {} ", currentIndex);

		if (prevIndex == currentIndex) {
			logger.debug("Cannot update location as both index are same");
			return false;
		}

		Integer updateLocationPostion = locationRepositry.updateLocationPostion(prevIndex, currentIndex);

		return updateLocationPostion == 1;
	}

	@Override
	public boolean createLocation(LocationRequest req) {
		logger.info("Creating location for {}", req);

		LocationDTO locDto = new LocationDTO();
		LocationEnitity ent = new LocationEnitity();

		locDto.setLocationName(req.getFLocationName());
		ent = setLocationPosition(locDto, ent);
		ent.setIsActive("Y");
		LocationEnitity save = locationRepositry.save(ent);

		return save != null;
	}

	private LocationEnitity setLocationPosition(LocationDTO locDto, LocationEnitity locationEnt) {
		boolean isLocationExixst;
		List<LocationEnitity> findByFLocationName = locationRepositry.findBylocationName(locDto.getLocationName());

		isLocationExixst = findByFLocationName.isEmpty();

		if (isLocationExixst) {
			Integer findMaxLocPos = locationRepositry.findMaxLocPos();
			if (findMaxLocPos == null)
				findMaxLocPos = 0;
			else
				findMaxLocPos++;

			locDto.setPosition(findMaxLocPos);
			BeanUtils.copyProperties(locDto, locationEnt);
		} else {
			locationEnt = findByFLocationName.get(0);
		}

		return locationEnt;
	}

}
