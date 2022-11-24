package be.abis.exercise.controller;

import be.abis.exercise.error.ApiError;
import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value= PersonAlreadyExistsException.class)
    protected ResponseEntity<? extends Object> handlePersonAlreadyExists(PersonAlreadyExistsException paee, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError err = new ApiError("person already exists", status.value(), paee.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value= PersonCanNotBeDeletedException.class)
    protected ResponseEntity<? extends Object> handlePersonCannotBeDeleted(PersonCanNotBeDeletedException pcbde, WebRequest request){
        HttpStatus status = HttpStatus.GONE;
        ApiError err = new ApiError("person cannot be deleted", status.value(), pcbde.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value= PersonNotFoundException.class)
    protected ResponseEntity<? extends Object> handlePersonNotFound(PersonNotFoundException pnfe, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError err = new ApiError("person not found", status.value(), pnfe.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

}
