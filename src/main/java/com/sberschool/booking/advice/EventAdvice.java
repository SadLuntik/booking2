package com.sberschool.booking.advice;

import com.sberschool.booking.exception.EventBookedException;
import com.sberschool.booking.exception.NotAllFieldsAreFilledInException;
import com.sberschool.booking.exception.NotValidDateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EventAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value =  NotAllFieldsAreFilledInException.class )
    protected ResponseEntity<Object> notAllFieldsAreFilledIn(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "please filling all field";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NO_CONTENT, request);
    }

    @ExceptionHandler(value =  NotValidDateException.class )
    protected ResponseEntity<Object> notValidDate(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Please enter valid date";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value =  EventBookedException.class )
    protected ResponseEntity<Object> eventBooked(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "At this time, another user is logged in to this event. Please choose another time";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
