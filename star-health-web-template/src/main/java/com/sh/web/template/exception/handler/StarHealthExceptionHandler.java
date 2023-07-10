package com.sh.web.template.exception.handler;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sh.web.template.exception.BusinessException;
import com.sh.web.template.exception.InvalidOccupationException;
import com.sh.web.template.exception.InvalidSumAssuredException;
import com.sh.web.template.exception.StarHealthBadRequestException;
import com.sh.web.template.model.StarHealthErrorResponseDTO;

@RestControllerAdvice
public class StarHealthExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(StarHealthExceptionHandler.class);
	@Autowired
	protected StarHealthErrorResponseDTO errorResponse;

	// Validate Request Body
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOGGER.error("Inside StarHealthExceptionHandler.handleMethodArgumentNotValid()");
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setStatus("Business Failure");
		errorResponse.setErrorMessage(errors);
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
		LOGGER.error("Error Response:" + errorResponse);
		return responseEntity;
	}

	// Validate Path and Query params
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StarHealthErrorResponseDTO> validatePathAndQueryParams(ConstraintViolationException exp) {
		LOGGER.error("Inside StarHealthExceptionHandler.validatePathAndQueryParams()");
		Set<ConstraintViolation<?>> violations = exp.getConstraintViolations();
		List<String> errList = new CopyOnWriteArrayList<>();
		for (ConstraintViolation<?> violation : violations) {
			errList.add(violation.getMessage());
		}
		errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setStatus("Business Failure");
		errorResponse.setErrorMessage(errList);
		ResponseEntity<StarHealthErrorResponseDTO> responseEntity = new ResponseEntity<StarHealthErrorResponseDTO>(
				errorResponse, HttpStatus.BAD_REQUEST);
		LOGGER.error("Error Response:" + errorResponse);
		return responseEntity;
	}

	@ExceptionHandler(InvalidOccupationException.class)
	public final ResponseEntity<StarHealthErrorResponseDTO> handleOccupationException(InvalidOccupationException ex) {
		LOGGER.error("Inside StarHealthExceptionHandler.handleOccupationException()");
		List<String> errList = new CopyOnWriteArrayList<>();
		errList.add(ex.getMessage());
		errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setStatus("Business Failure");
		errorResponse.setErrorMessage(errList);
		LOGGER.error("Error Response:" + errorResponse);
		return new ResponseEntity<StarHealthErrorResponseDTO>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidSumAssuredException.class)
	public final ResponseEntity<StarHealthErrorResponseDTO> handleSumAssuredException(InvalidSumAssuredException ex) {
		LOGGER.error("Inside StarHealthExceptionHandler.handleSumAssuredException()");
		List<String> errList = new CopyOnWriteArrayList<>();
		errList.add(ex.getMessage());
		errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setStatus("Business Failure");
		errorResponse.setErrorMessage(errList);
		LOGGER.error("Error Response:" + errorResponse);
		return new ResponseEntity<StarHealthErrorResponseDTO>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(StarHealthBadRequestException.class)
	public final ResponseEntity<StarHealthErrorResponseDTO> handleBadRequestException(
			StarHealthBadRequestException ex) {
		LOGGER.error("Inside StarHealthExceptionHandler.handleBadRequestException()");
		List<String> errList = new CopyOnWriteArrayList<>();
		errList.add(ex.getMessage());
		errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setStatus("Business Failure");
		errorResponse.setErrorMessage(errList);
		LOGGER.error("Error Response:" + errorResponse);
		return new ResponseEntity<StarHealthErrorResponseDTO>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RestClientException.class)
	public final ResponseEntity<StarHealthErrorResponseDTO> handleRestTemplateExceptions(RestClientException ex) {
		LOGGER.error("Inside StarHealthExceptionHandler.handleRestTemplateExceptions()");
		List<String> errList = new CopyOnWriteArrayList<>();
		errList.add(ex.getMessage());
		errorResponse.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE.value());
		errorResponse.setStatus("Business Failure");
		errorResponse.setErrorMessage(errList);
		LOGGER.error("Error Response:" + errorResponse);
		return new ResponseEntity<StarHealthErrorResponseDTO>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(WebServiceException.class)
	public final ResponseEntity<StarHealthErrorResponseDTO> handleWebServiceException(WebServiceException ex) {
		LOGGER.error("Inside StarHealthExceptionHandler.handleWebServiceException()");
		List<String> errList = new CopyOnWriteArrayList<>();
		errList.add(ex.getMessage());
		errorResponse.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE.value());
		errorResponse.setStatus("Business Failure");
		errorResponse.setErrorMessage(errList);
		LOGGER.error("Error Response:" + errorResponse);
		return new ResponseEntity<StarHealthErrorResponseDTO>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<StarHealthErrorResponseDTO> handleBusinessException(BusinessException ex) {
		LOGGER.error("Inside StarHealthExceptionHandler.handleBusinessException()");
		List<String> errList = new CopyOnWriteArrayList<>();
		errList.add(ex.getMessage());
		errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setStatus("Business Failure");
		errorResponse.setErrorMessage(errList);
		LOGGER.error("Error Response:" + errorResponse);
		return new ResponseEntity<StarHealthErrorResponseDTO>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<StarHealthErrorResponseDTO> handleAllExceptions(Exception ex) {
		LOGGER.error("Inside StarHealthExceptionHandler.handleAllExceptions()");
		List<String> errList = new CopyOnWriteArrayList<>();
		errList.add(ex.getMessage());
		errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setStatus("Business Failure");
		errorResponse.setErrorMessage(errList);
		LOGGER.error("Error Response:" + errorResponse);
		return new ResponseEntity<StarHealthErrorResponseDTO>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
