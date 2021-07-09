package com.duroflex.neuma.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "roles",schema = "neuma")
public class ActorRole implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name="roles",length = 20)
    private ERole name;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date createdAt;

    @Column(name = "role_status")
    private Boolean roleStatus;

    public ActorRole() {

    }

    public ActorRole(ERole name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }




    public Boolean getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(Boolean roleStatus) {
        this.roleStatus = roleStatus;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}