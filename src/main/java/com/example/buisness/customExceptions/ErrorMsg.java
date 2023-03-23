package com.example.buisness.customExceptions;

import lombok.Getter;

@Getter
public enum ErrorMsg {
    ID_EXIST("Id Exist"),
    NOT_EXIST("Not Exist"),
    ALREADY_EXIST("Already Exist");

    private  String error;

    ErrorMsg(String error) {
        this.error = error;
    }
}
