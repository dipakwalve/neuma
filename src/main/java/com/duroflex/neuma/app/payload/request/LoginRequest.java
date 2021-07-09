package com.duroflex.neuma.app.payload.request;

import javax.validation.constraints.NotBlank;;

public class LoginRequest {
    @NotBlank
    private String mobileNo;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

}
