package com.mindfire.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindfire.config.CustomHttpErrorException;
import com.mindfire.dto.APIError;

/**
 * Global error handler
 * 
 * @author sibananda
 *
 */
@Controller
@RestControllerAdvice
public class CustomHttpErrorController {

	/**
	 * Handle HTTP error
	 * 
	 * @param ex contains exception info
	 * @return Response wrapping it on {@link ResponseEntity}
	 */
	@ExceptionHandler(value = CustomHttpErrorException.class)
	public ResponseEntity<APIError> handelCustomError(CustomHttpErrorException ex) {

		APIError errorResp = new APIError();
		errorResp.setTimeStamp(LocalDateTime.now());
		errorResp.setMessage(ex.getMessage());
		errorResp.setStatusCode(ex.getHttpStatus().toString());
		if (ex.getMessage().contains("1006")) {
			errorResp.setMessage("No matching location found");
			errorResp.setStatusCode("1006");
		}
		return new ResponseEntity<>(errorResp, HttpStatus.OK);
	}
}
