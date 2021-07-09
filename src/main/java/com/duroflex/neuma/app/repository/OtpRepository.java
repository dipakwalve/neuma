package com.duroflex.neuma.app.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.duroflex.neuma.app.model.OTP;

@Repository
public interface OtpRepository extends JpaRepository<OTP, Long> {

    public OTP verifyOtp(String otp, Integer userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE neuma.OTP set mobile_verify_status =:mobileVerifyStatus where otp_id = :otpId", nativeQuery = true)
    public Integer verifyOtpStatus(@Param("mobileVerifyStatus") String mobileVerifyStatus, @Param("otpId") Integer otpId);

}
