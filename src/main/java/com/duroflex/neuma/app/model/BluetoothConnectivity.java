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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "bluetooth_connection", schema = "neuma")
public class BluetoothConnectivity implements Serializable {

    /**
     * @author Deepak Walve date 01-06-2021
     */
    private static final long serialVersionUID = -5944493582089286705L;

    @Id
    @Column(name = "bluetooth_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bluetoothId;
    @Column(name = "message  " + "")
    private String message;
    @Column(name = "paired__device_id")
    private Boolean pairedDeviceId;
    @Column(name = "mattress_id")
    private Integer mattressId;
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dateTime;

    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="paired_status")
    private boolean pairedStatus;


//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "neuma_user", schema = "neuma", joinColumns = @JoinColumn(name = "bluetooth_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//	private Set<ActorDetails> actor;

    public Integer getBluetoothId() {
        return bluetoothId;
    }

    public void setBluetoothId(Integer bluetoothId) {
        this.bluetoothId = bluetoothId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getPairedDeviceId() {
        return pairedDeviceId;
    }

    public void setPairedDeviceId(Boolean pairedDeviceId) {
        this.pairedDeviceId = pairedDeviceId;
    }

    public Integer getMattressId() {
        return mattressId;
    }

    public void setMattressId(Integer mattressId) {
        this.mattressId = mattressId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "BluetoothConnectivity [bluetoothId=" + bluetoothId + ", message=" + message + ", pairedDeviceId="
                + pairedDeviceId + ", mattressId=" + mattressId + ", dateTime=" + dateTime + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt + ", userId=" + userId + ", pairedStatus=" + pairedStatus + "]";
    }

    public BluetoothConnectivity() {
        super();
        // TODO Auto-generated constructor stub
    }
    public BluetoothConnectivity(Integer bluetoothId, String message, Boolean pairedDeviceId, Integer mattressId,
                                 Date dateTime, Date createdAt, Date updatedAt, Integer userId, boolean pairedStatus) {
        super();
        this.bluetoothId = bluetoothId;
        this.message = message;
        this.pairedDeviceId = pairedDeviceId;
        this.mattressId = mattressId;
        this.dateTime = dateTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.pairedStatus = pairedStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isPairedStatus() {
        return pairedStatus;
    }

    public void setPairedStatus(boolean pairedStatus) {
        this.pairedStatus = pairedStatus;
    }


//	public Set<ActorDetails> getActor() {
//		return actor;
//	}
//
//	public void setActor(Set<ActorDetails> actor) {
//		this.actor = actor;
//	}



}
