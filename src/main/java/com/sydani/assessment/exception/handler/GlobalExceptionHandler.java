package com.sydani.assessment.exception.handler;

import com.sydani.assessment.exception.ApiException;
import com.sydani.assessment.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.ConnectException;
import java.util.NoSuchElementException;
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {IllegalArgumentException.class, NoSuchElementException.class})
    protected ResponseEntity<?> handleConflict(Exception ex, WebRequest request) throws Exception {

        //add headers to be returned with response
        HttpHeaders headers = new HttpHeaders();


        if (ex instanceof IllegalArgumentException) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ApiException(ex.getMessage()), HttpStatus.BAD_REQUEST);

        }else if (ex instanceof NoSuchElementException) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ApiException(ex.getMessage()), HttpStatus.NOT_FOUND);

        }else if (ex instanceof ConnectException) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ApiException(ex.getMessage()), HttpStatus.NOT_FOUND);

        } else {
            // rethrow the given exception for further processing through the HandlerExceptionResolver chain.
            throw ex;
        }
    }

}
