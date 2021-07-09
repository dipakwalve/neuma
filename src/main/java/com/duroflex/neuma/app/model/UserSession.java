package com.duroflex.neuma.app.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_session", schema = "neuma")
@NamedQuery(name = "UserSession.findByUserId", query = "from UserSession u where u.userId=: userId and u.loginFlag=true")
@NamedQuery(name = "UserSession.logoutSession", query = "update UserSession s SET s.logoutTime =: logoutTime,s.loginFlag=:loginFlag where s.sessionId =: sessionId ")
public class UserSession {

    @Column(name = "session_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer sessionId;

    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date lastLogin;
    @Column(name = "logout_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logoutTime;
    @Column(name = "login_flag")
    private Boolean loginFlag;
    @Column(name = "user_id")
    private Integer userId;

    public UserSession() {
    }

    public UserSession(Integer sessionId, Date lastLogin, Date logoutTime, Boolean loginFlag, Integer userId) {
        this.sessionId = sessionId;
        this.lastLogin = lastLogin;
        this.logoutTime = logoutTime;
        this.loginFlag = loginFlag;
        this.userId = userId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Boolean getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(Boolean loginFlag) {
        this.loginFlag = loginFlag;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "sessionId=" + sessionId +

                ", lastLogin=" + lastLogin +
                ", logoutTime=" + logoutTime +
                ", loginFlag=" + loginFlag +
                ", userId=" + userId +
                '}';
    }
}
