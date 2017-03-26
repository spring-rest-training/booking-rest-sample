package com.bookingsample.inventory.web;

/**
 * Created by davut on 12.03.2017.
 */
public class RestException extends RuntimeException
{
    public static final int NOT_FOUND = 1;
    public static final int RECORD_EXIST = 2;
    public static final int UNEXPECTED_ERROR = 999;
    int errorCode;
    String description;

    public RestException(int errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }
}
