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

/**
 *
 * @author Deepak Walve date 09-06-2021
 *
 */
@Entity
@Table(name = "error_notification",schema = "neuma")
public class ErrorNotification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Integer notificationId;

    @Column(name = "notification_code")
    private Integer notificationCode;

    @Column(name = "notification_message")
    private String notificationMessage;

    @Column(name = "language")
    private String language;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "active_status")
    private Boolean activeStatus;

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Integer getNotificationCode() {
        return notificationCode;
    }

    public void setNotificationCode(Integer notificationCode) {
        this.notificationCode = notificationCode;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public ErrorNotification(Integer notificationId, Integer notificationCode, String notificationMessage,
                             String language, Date updatedAt, Date createdAt, Boolean activeStatus) {
        super();
        this.notificationId = notificationId;
        this.notificationCode = notificationCode;
        this.notificationMessage = notificationMessage;
        this.language = language;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.activeStatus = activeStatus;
    }

    @Override
    public String toString() {
        return "ErrorNotofication [notificationId=" + notificationId + ", notificationCode=" + notificationCode
                + ", notificationMessage=" + notificationMessage + ", language=" + language + ", updatedAt=" + updatedAt
                + ", createdAt=" + createdAt + ", activeStatus=" + activeStatus + "]";
    }

    public ErrorNotification() {
        super();
        // TODO Auto-generated constructor stub
    }

}
