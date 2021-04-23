package ua.pinger.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.pinger.dto.ErrorDto;
import ua.pinger.exception.RestApiException;

@ControllerAdvice
public class ExceptionHandlerAdvice
{
    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleRestApiException(RestApiException e){

        return ResponseEntity.status(e.getStatus()).body(new ErrorDto(e));
    }


}

