package com.mindfire.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindfire.dto.APIError;
import com.mindfire.exceptions.LocationNotFound;

/**
 * 
 * @author sibananda
 *
 *         Custom error handler for handling the error globally
 */
@RestControllerAdvice
public class CustomErrorController {

	/**
	 * Handle exception if Particular Location is not found
	 * 
	 * @param ex contains exception details
	 * @return
	 */
	@ExceptionHandler(value = LocationNotFound.class)
	public ResponseEntity<APIError> handelLocationNotFoundError(LocationNotFound ex) {
		APIError errorResp = new APIError();
		errorResp.setTimeStamp(LocalDateTime.now());
		errorResp.setMessage(ex.getMessage());

		return new ResponseEntity<>(errorResp, HttpStatus.OK);
	}
}
