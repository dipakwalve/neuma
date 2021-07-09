package com.duroflex.neuma.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "cities", schema = "neuma")
public class Cities implements Serializable {

    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cityId;
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "city_state")
    private String cityState;
    @Column(name = "active_status")
    private Boolean activeStatus;
    @Column(name = "country")
    private String country;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityState() {
        return cityState;
    }

    public void setCityState(String cityState) {
        this.cityState = cityState;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Cities [cityId=" + cityId + ", cityName=" + cityName + ", cityState=" + cityState + ", activeStatus="
                + activeStatus + ", country=" + country + "]";
    }

    public Cities(Integer cityId, String cityName, String cityState, Boolean activeStatus, String country) {
        super();
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityState = cityState;
        this.activeStatus = activeStatus;
        this.country = country;
    }

    public Cities() {
        super();
        // TODO Auto-generated constructor stub
    }


}
