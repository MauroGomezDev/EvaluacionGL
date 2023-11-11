package com.globallogic.evaluacion.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ResponseException {
    private Date timestamp;
    private int code;
    private String detail;

    public ResponseException(int code, String detail) {
        this.timestamp = new Date();
        this.code = code;
        this.detail = detail;
    }

    // Getters y setters
}
