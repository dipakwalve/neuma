package com.duroflex.neuma.app.payload.response;


public class JwtResponse {
    private String token;
    private String type = "Indium";
    private Integer id;
    private String message;


    public JwtResponse(String accessToken, Integer id,String message) {
        this.token = accessToken;
        this.id = id;
        this.message=message;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
