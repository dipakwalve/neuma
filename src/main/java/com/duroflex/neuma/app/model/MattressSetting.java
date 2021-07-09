package com.duroflex.neuma.app.model;

import com.duroflex.neuma.app.util.HashMapConverter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "mattress_setting", schema = "neuma")
@NamedQuery(name = "MattressSetting.updateMattressSetting", query = "update MattressSetting set firmnessData = :firmnessData where mattressSettingId=:mattressSettingId")
@NamedQuery(name = "MattressSetting.findById", query = "from MattressSetting ms where ms.mattressSettingId =:mattressSettingId")
@NamedQuery(name = "MattressSetting.findByMattressId", query = "from MattressSetting m where m.mattressId =:mattressId")
@NamedQuery(name = "MattressSetting.orderByMattressId", query = "from MattressSetting m where m.mattressId =:mattressId order by m.updatedAt desc")
@NamedQuery(name = "MattressSetting.findByMattressSetting", query = "from MattressSetting m where m.mattressId =:mattressId and m.activateSetting=true")
@NamedQuery(name = "MattressSetting.updateStatus", query = "update MattressSetting m set m.activateSetting =: activateSetting where m.mattressSettingId =: mattressSettingId")
@NamedQuery(name = "MattressSetting.setTargetedValue", query = "update MattressSetting m set m.targetedValue =: targetedValue where m.mattressId=: mattressId")
@NamedQuery(name = "MattressSetting.findListAllMattressSetting", query = "from MattressSetting m where m.mattressId =:mattressId and m.activateSetting=true")
@NamedQuery(name = "MattressSetting.getTargetedValue", query = "select m.targetedValue from MattressSetting m where m.mattressId =:mattressId and m.activateSetting=true  ")
@NamedQuery(name = "MattressSetting.updateFavoriteFirmness", query = "update MattressSetting m set m.firmnessData =: firmnessData where m.mattressSettingId=: mattressSettingId")
@NamedQuery(name = "MattressSetting.updateSettingStatus",query = "update MattressSetting m set m.settingActiveStatus = false where m.mattressId =: mattressId and m.activateSetting=true")
public class MattressSetting implements Serializable {

    /**
     * @author Deepak Walve Date 01-06-2021
     */
    private static final long serialVersionUID = 8368479396533980769L;

    @Id
    @Column(name = "mattress_setting_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mattressSettingId;

    @Column(name = "firmness_data", columnDefinition = "JSON")
    private String firmnessData;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "targeted_value")
    private short targetedValue;

    @Column(name = "mattress_id")
    private Integer mattressId;

    @Column(name = "activate_setting")
    private Boolean activateSetting;

    @Transient
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> jsonAttribute;

    @Column(name = "setting_active_status")
    private Boolean settingActiveStatus;

    public MattressSetting() {
        super();
    }

    public MattressSetting(Integer mattressSettingId, String firmnessData, Date createdAt, Date updatedAt, short targetedValue, Integer mattressId, Boolean activateSetting, Boolean settingActiveStatus) {
        this.mattressSettingId = mattressSettingId;
        this.firmnessData = firmnessData;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.targetedValue = targetedValue;
        this.mattressId = mattressId;
        this.activateSetting = activateSetting;
        this.settingActiveStatus = settingActiveStatus;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getMattressSettingId() {
        return mattressSettingId;
    }

    public void setMattressSettingId(Integer mattressSettingId) {
        this.mattressSettingId = mattressSettingId;
    }

    public Integer getMattressId() {
        return mattressId;
    }

    public void setMattressId(Integer mattressId) {
        this.mattressId = mattressId;
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

    public String getFirmnessData() {
        return firmnessData;
    }

    public void setFirmnessData(String firmnessData) {
        this.firmnessData = firmnessData;
    }

    public Boolean getActivateSetting() {
        return activateSetting;
    }

    public void setActivateSetting(Boolean activateSetting) {
        this.activateSetting = activateSetting;
    }

    @Override
    public String toString() {
        return "MattressSetting{" +
                "mattressSettingId=" + mattressSettingId +
                ", firmnessData='" + firmnessData + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", targetedValue=" + targetedValue +
                ", mattressId=" + mattressId +
                ", activateSetting=" + activateSetting +

                '}';
    }

    public short getTargetedValue() {

        return targetedValue;
    }

    public void setTargetedValue(short targetedValue) {
        this.targetedValue = targetedValue;
    }

    public Boolean getSettingActiveStatus() {
        return settingActiveStatus;
    }

    public void setSettingActiveStatus(Boolean settingActiveStatus) {
        this.settingActiveStatus = settingActiveStatus;
    }

}
