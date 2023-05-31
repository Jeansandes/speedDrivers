package com.sandes.speedyDrive.controllers.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sandes.speedyDrive.services.exceptions.EntityNotCreatedException;
import com.sandes.speedyDrive.services.exceptions.EntityNotFoundException;
import com.sandes.speedyDrive.services.exceptions.EntityNotUpdadeOrDeleteException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> addressNotNull(IllegalArgumentException e, HttpServletRequest request){
		StandardError err = message(e, request);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<StandardError> addressNotValid(SQLIntegrityConstraintViolationException e, HttpServletRequest request){
		StandardError err = message(e, request);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> argumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.CONFLICT.value());
		err.setError("conflito na aplicação!");
		err.setMessage(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
		StandardError err = message(e, request);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}
	
	@ExceptionHandler(EntityNotCreatedException.class)
	public ResponseEntity<StandardError> entityNotCreated(EntityNotCreatedException e, HttpServletRequest request){
		StandardError err = message(e, request);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}
	
	@ExceptionHandler(EntityNotUpdadeOrDeleteException.class)
	public ResponseEntity<StandardError> entityNotUpdateorDelete(EntityNotUpdadeOrDeleteException e, HttpServletRequest request){
		StandardError err = message(e, request);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}
	
	public StandardError message(Exception e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.CONFLICT.value());
		err.setError("conflito na aplicação!");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return err;
	}
	
}
