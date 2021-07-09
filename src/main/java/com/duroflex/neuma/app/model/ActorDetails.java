package com.duroflex.neuma.app.model;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Deepak Walve Date: 26-MAY-2021
 */
@Entity
@Table(name = "user_details", schema = "neuma")
@NamedQuery(name = "ActorDetails.updateFullName", query = "update ActorDetails a SET a.fullName =: fullName where a.userId =: userId")
@NamedQuery(name = "ActorDetails.getBootstrappingStatus", query = "select a.bootstrappingStatus from ActorDetails a where userId=: userId")
@NamedQuery(name = "ActorDetails.updateBootstrappingStatus", query = "update ActorDetails a SET a.bootstrappingStatus =: bootstrappingStatus where a.userId =: userId ")
@AllArgsConstructor
public class ActorDetails implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "pincode")
    private Integer pincode;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "gender")
    private String gender;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "bootstrapping_status")
    private Boolean bootstrappingStatus;

    @Column(name = "user_token")
    private String userToken;

    @Column(name = "user_status")
    private Boolean activeStatus;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", schema = "neuma", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<ActorRole> roles = new HashSet<ActorRole>();

    public ActorDetails() {
        super();
    }

    public ActorDetails(Integer userId, String fullName) {
        this.userId = userId;
        this.fullName = fullName;
    }

    public ActorDetails(String mobileNo, String emailId, String address, String fullName, Boolean activeStatus) {

        this.mobileNo = mobileNo;
        this.activeStatus = activeStatus;
        this.emailId = emailId;
        this.address = address;
        this.fullName = fullName;

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<ActorRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<ActorRole> roles) {
        this.roles = roles;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = false;
    }

    public Boolean getBootstrappingStatus() {
        return bootstrappingStatus;
    }

    public void setBootstrappingStatus(Boolean bootstrappingStatus) {
        this.bootstrappingStatus = bootstrappingStatus;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
