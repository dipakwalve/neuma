package com.duroflex.neuma.app.payload.response;

public class PlainMessageResponse {
    private  String message;

    public PlainMessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
