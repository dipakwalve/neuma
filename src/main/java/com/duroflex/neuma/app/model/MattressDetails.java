package com.duroflex.neuma.app.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "mattress_details", schema = "neuma")
@NamedNativeQuery(name = "MattressDetails.findByMattressIdentifier", query = "select * from neuma.mattress_details m where m.mattress_identifier = ?1 and user_id=?2 ", resultClass = MattressDetails.class)
@NamedQuery(name = "MattressDetails.findByUserId", query = "from MattressDetails m where m.userId=:userId")
@NamedQuery(name = "MattressDetails.findMattressIdentifier", query = "from MattressDetails m where m.mattressIdentifier =: mattressIdentifier")
@NamedQuery(name = "MattressDetails.updateSideDetails", query = "update MattressDetails m SET m.mattressSide =: mattressSide where m.mattressId =: mattressId ")
@NamedQuery(name = "MattressDetails.findByMattressIdentifierAndSide", query = "from MattressDetails m where m.mattressIdentifier=: mattressIdentifier and m.mattressSide = : mattressSide")
@NamedQuery(name = "MattressDetails.modifyMattressSideNames", query = "update MattressDetails m Set m.mattressSideName =: mattressSideName, m.userId=: userId where m.mattressId =: mattressId")
@NamedQuery(name = "MattressDetails.countMattressIdentifier", query = "select count(mattressIdentifier) from MattressDetails m where m.mattressIdentifier =: mattressIdentifier")
public class MattressDetails implements Serializable {

    /**
     * @author Deepak Walve Date 01-06-2021
     */
    private static final long serialVersionUID = -4000691183683064049L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mattress_id")
    private Integer mattressId;

    @Column(name = "mattress_identifier")
    private String mattressIdentifier;

    @Column(name = "mattress_side")
    private String mattressSide;

    @Column(name = "paired_device_status")
    private boolean pairedDeviceStatus;

    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "mattress_side_name")
    private String mattressSideName;

    @Column(name = "user_id")
    private Integer userId;

    public MattressDetails(Integer mattressId, String mattressIdentifier, String mattressSide,
                           boolean pairedDeviceStatus, Date createdAt, Date updatedAt) {
        super();
        this.mattressId = mattressId;
        this.mattressIdentifier = mattressIdentifier;
        this.mattressSide = mattressSide;
        this.pairedDeviceStatus = pairedDeviceStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public MattressDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getMattressId() {
        return mattressId;
    }

    public void setMattressId(Integer mattressId) {
        this.mattressId = mattressId;
    }

    public String getMattressIdentifier() {
        return mattressIdentifier;
    }

    public void setMattressIdentifier(String mattressIdentifier) {
        this.mattressIdentifier = mattressIdentifier;
    }

    public String getMattressSide() {
        return mattressSide;
    }

    public void setMattressSide(String mattressSide) {
        this.mattressSide = mattressSide;
    }

    public boolean isPairedDeviceStatus() {
        return pairedDeviceStatus;
    }

    public void setPairedDeviceStatus(boolean pairedDeviceStatus) {
        this.pairedDeviceStatus = pairedDeviceStatus;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMattressSideName() {
        return mattressSideName;
    }

    public void setMattressSideName(String mattressSideName) {
        this.mattressSideName = mattressSideName;
    }

    @Override
    public String toString() {
        return "MattressDetails{" +
                "mattressId=" + mattressId +
                ", mattressIdentifier='" + mattressIdentifier + '\'' +
                ", mattressSide='" + mattressSide + '\'' +
                ", pairedDeviceStatus=" + pairedDeviceStatus +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", mattressSideName='" + mattressSideName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
