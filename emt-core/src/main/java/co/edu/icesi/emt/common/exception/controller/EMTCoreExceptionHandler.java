package co.edu.icesi.emt.common.exception.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.edu.icesi.emt.common.exception.dto.ExceptionResponse;
import co.edu.icesi.emt.common.exception.model.EMTException;
import co.edu.icesi.emt.common.exception.model.RootAdminCanNotBeRemovedException;
import co.edu.icesi.emt.common.exception.model.UserAccountDisabledException;
import co.edu.icesi.emt.common.exception.model.UserIsNotAdminException;
import co.edu.icesi.emt.common.exception.model.UserNotFoundException;
import co.edu.icesi.emt.common.exception.model.UsernameAlreadyExistsException;

@ControllerAdvice
public class EMTCoreExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException exception, WebRequest webRequest) {
        return this.handle(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RootAdminCanNotBeRemovedException.class)
    public ResponseEntity<Object> handleRootAdminCanNotBeRemoved(RootAdminCanNotBeRemovedException exception,
            WebRequest webRequest) {
        return this.handle(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAccountDisabledException.class)
    public ResponseEntity<Object> handleUserAccountDisabled(UserAccountDisabledException exception,
            WebRequest webRequest) {
        return this.handle(exception, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserIsNotAdminException.class)
    public ResponseEntity<Object> handleUserIsNotAdmin(UserIsNotAdminException exception, WebRequest webRequest) {
        return this.handle(exception, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Object> handleUsernameAlreadyExists(UsernameAlreadyExistsException exception,
            WebRequest webRequest) {
        return this.handle(exception, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> handle(EMTException exception, HttpStatus status) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage(exception.getDomainMessage());
        ResponseEntity<Object> entity = new ResponseEntity<>(response, status);
        return entity;
    }
}