package com.stackroute.muzixmanager.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class MuzixRestControllerAdvice {

	@ExceptionHandler(value = {MusicTrackAlreadyExistsException.class})
	public String musicAlreadyExistsException(MusicTrackAlreadyExistsException s) {
		return s.getMessage();
	}
	@ExceptionHandler(value = {MusicTrackNotFoundException.class})
	public String musicTrackNotFoundException(MusicTrackNotFoundException s) {
		return s.getMessage();
	}

	@ExceptionHandler(value = {Exception.class})
	public String handleBase(Exception e) {
		return e.getMessage();
	}

}

