package com.mindfire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.dto.APIResponse;
import com.mindfire.dto.LocationRequest;
import com.mindfire.dto.MaxMinTempDto;
import com.mindfire.dto.UpdateLocationRequest;
import com.mindfire.service.ILocationService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/location")
@Api(value = "/location")
public class LocationController {

	@Autowired
	private ILocationService lsimp;

	@ApiOperation(value = "Fetch MAX and MIN Temp info for avilable location", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Return gatherd weather data"),
			@ApiResponse(code = 404, message = "No Record Found") })

	@GetMapping(path = "/all")

	@CircuitBreaker(fallbackMethod = "setIntermidiateServiceIssue", name = "ISE")
	public ResponseEntity<?> getAllAvilableLocation() {
		List<MaxMinTempDto> fetchEdAllLocation = lsimp.fetchAllLocation();
		if (!fetchEdAllLocation.isEmpty()) {
			return ResponseEntity.ok(fetchEdAllLocation);
		} else {
			APIResponse apiResponse = new APIResponse();
			apiResponse.setMessage("No Data Found");

			return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Fallback method for execution if Location service is down
	 * 
	 * @param e contains exception object for failure reason
	 * @return API response
	 */
	public ResponseEntity<APIResponse> setIntermidiateServiceIssue(Exception e) {
		APIResponse apiResponse = new APIResponse();
		apiResponse.setMessage("Taking Longer Than Expected....Please try after some time");

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@ApiOperation(value = "Fetch MAX and MIN Temp info for avilable location", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Return gatherd weather data"),
			@ApiResponse(code = 404, message = "No Record Found") })

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createLocation(@RequestBody LocationRequest req) {

		boolean isCreated = lsimp.createLocation(req);
		if (isCreated) {
			APIResponse apiResponse = new APIResponse();
			apiResponse.setMessage("Location Created");

			return ResponseEntity.ok(apiResponse);
		} else {
			APIResponse apiResponse = new APIResponse();
			apiResponse.setMessage("Location Cannot Created");

			return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "/updateloc", notes = "Update the location position", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Location position updated"),
			@ApiResponse(code = 404, message = "No Record Found") })

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateLocation(@RequestBody UpdateLocationRequest req) {

		boolean isUpdated = lsimp.updateLocation(req.getPrevIndex(), req.getCurrentIndex());
		if (isUpdated) {
			APIResponse apiResponse = new APIResponse();
			apiResponse.setMessage("Location Updated");

			return ResponseEntity.ok(apiResponse);
		} else {
			APIResponse apiResponse = new APIResponse();
			apiResponse.setMessage("Location Cannot Updated");

			return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Soft delete particular location by given location id
	 * @param locationId of the location user want to delete
	 * @return 
	 */
	@ApiOperation(value = "Delete Location by location id", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Location Deleted"), })
	
	@DeleteMapping(path = "/{locationid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<APIResponse> deleteLocation(@PathVariable("locationid") Integer locationId) {

		APIResponse apiResponse = new APIResponse();

		lsimp.deleteLocation(locationId);
		apiResponse.setMessage("Location Deleted");

		return ResponseEntity.ok(apiResponse);
	}
}
