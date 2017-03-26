package com.bookingsample.inventory.web;

import com.bookingsample.inventory.data.Room;

import static com.bookingsample.inventory.web.RestException.UNEXPECTED_ERROR;

/**
 * Created by davut on 12.03.2017.
 */
public class ApiResponse {

    public static final int FAILED = 1;

    public static ApiResponse success(Object data)
    {
        ApiResponse response = new ApiResponse();
        response.status = STATUS.OK;
        response.data = data;
        return response;
    }

    public static ApiResponse error(RestException e) {
        ApiResponse response = new ApiResponse();
        response.error = new ApiError(e.getErrorCode(), e.getDescription());
        return response;
    }

    public static ApiResponse error(Exception e) {
        ApiResponse response = new ApiResponse();
        response.error = new ApiError(UNEXPECTED_ERROR, "Undefined error occured");
        return response;
    }

    public static enum STATUS
    {
        OK , ERROR
    }
    public static final class ApiError
    {
        int errorCode;

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        String description;

        public ApiError(int errorCode, String description) {
            this.errorCode = errorCode;
            this.description = description;
        }
    }
    private STATUS status;
    private Object data;
    private ApiError error;

    public STATUS getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public ApiError getError() {
        return error;
    }
}
