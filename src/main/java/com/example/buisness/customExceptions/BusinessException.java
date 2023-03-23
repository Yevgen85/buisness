package com.example.buisness.customExceptions;

public class BusinessException extends Exception{
    public BusinessException(ErrorMsg errorMsg) {
        super(errorMsg.getError());
    }
}
