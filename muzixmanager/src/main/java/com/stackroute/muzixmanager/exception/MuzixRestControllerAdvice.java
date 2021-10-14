package com.stackroute.muzixmanager.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class MuzixRestControllerAdvice {

	@ExceptionHandler(value = {SongAlreadyExistsException.class})
	public String SongAlreadyExistsException(SongAlreadyExistsException s) {
		return s.getMessage();
	}

	@ExceptionHandler(value = {Exception.class})
	public String handleBase(Exception e) {
		return e.getMessage();
	}

}
