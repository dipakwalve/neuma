package com.duroflex.neuma.app.payload.response;

public class MessageResponse {
    private String message;

    private Integer userId;

    public MessageResponse(String message) {
        this.message = message;
    }
    public MessageResponse(String message,Integer userId) {
        this.message = message;
        this.userId=userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
