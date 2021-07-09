package com.duroflex.neuma.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Entity
@Table(name = "otp_details", schema = "neuma")
@NamedQuery(name = "OTP.verifyOtp", query = "FROM OTP o WHERE o.otp = : otp  and o.userId= : userId ")
@Data
public class OTP implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7434600511061187394L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "otp_id")
    private Integer otpId;

    @Column(name = "otp")
    private String otp;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "mobile_verify_status")
    private Boolean mobileVerifyStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "otp_verify_timestamp")
    private Date otpVerifyTimestamp;

    @Column(name = "otp_flag")
    private boolean otpFlag;

    @Column(name = "user_id")
    private Integer userId;

    public Integer getOtpId() {
        return otpId;
    }

    public void setOtpId(Integer otpId) {
        this.otpId = otpId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Boolean getMobileVerifyStatus() {
        return mobileVerifyStatus;
    }

    public void setMobileVerifyStatus(Boolean mobileVerifyStatus) {
        this.mobileVerifyStatus = mobileVerifyStatus;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    public Date getOtpVerifyTimestamp() {
        return otpVerifyTimestamp;
    }

    public void setOtpVerifyTimestamp(Date otpVerifyTimestamp) {
        this.otpVerifyTimestamp = otpVerifyTimestamp;
    }

    public boolean isOtpFlag() {
        return otpFlag;
    }

    public void setOtpFlag(boolean otpFlag) {
        this.otpFlag = otpFlag;
    }



    public OTP(Integer otpId, String otp, Date createdAt, Date updatedAt, Boolean mobileVerifyStatus,
               Date otpVerifyTimestamp, boolean otpFlag, Integer userId) {
        super();
        this.otpId = otpId;
        this.otp = otp;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.mobileVerifyStatus = mobileVerifyStatus;
        this.otpVerifyTimestamp = otpVerifyTimestamp;
        this.otpFlag = otpFlag;
        this.userId = userId;
    }

    public OTP() {
        super();
    }

    @Override
    public String toString() {
        return "OTP [otpId=" + otpId + ", otp=" + otp + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
                + ", mobileVerifyStatus=" + mobileVerifyStatus + ", userId=" + userId + "]";
    }

}
