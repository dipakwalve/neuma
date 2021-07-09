package com.duroflex.neuma.app.payload.request;


import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {

    @NotBlank
    @Size(min = 10, max = 10)
    private String mobileNo;

    @NotBlank
    @Size(max = 50)
    @Email
    private String emailId;

    private String fullName;

    private String address;

    private Set<String> role;

//	@NotBlank
//	@Size(min = 6, max = 20)
//	private String password;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String email) {
        this.emailId = email;
    }

//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        role.add("USER");
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
