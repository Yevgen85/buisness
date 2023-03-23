package com.example.buisness.advice;

import com.example.buisness.customExceptions.BusinessException;
import com.example.buisness.customExceptions.ErrorObj;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AllControllerAdvice {
    @ResponseBody
    @ExceptionHandler(value = {BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleError(Exception e) {
        return new ErrorObj("Error", e.getMessage());
    }
}
