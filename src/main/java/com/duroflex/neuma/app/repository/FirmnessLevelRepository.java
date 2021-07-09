package com.duroflex.neuma.app.repository;

import com.duroflex.neuma.app.model.MattressSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FirmnessLevelRepository extends JpaRepository<MattressSetting, Long> {

    /**
     * @param mattressId
     * @return
     * @apiNote get the mattress all data by mattress id
     */
    List<MattressSetting> findByMattressId(Integer mattressId);

    List<MattressSetting> orderByMattressId(Integer mattressId);

    @Transactional
    @Modifying
    Integer updateMattressSetting(String firmnessData, Integer mattressSettingId);

    @Transactional
    @Modifying
    Integer activateSetting(Integer mattressSettingId);

    List<MattressSetting> findById(Integer mattressSettingId);

    //select only activate mattress setting using id.
    MattressSetting findByMattressSetting(Integer mattressId);

    // update mattress status activate to deactivate(it would be true or false )only
    @Transactional
    @Modifying
    int updateStatus(Integer mattressSettingId, boolean activateSetting);

    @Transactional
    @Modifying
    Integer setTargetedValue(short targetedValue, Integer mattressId);

    //list all mattress which is activate currently
    List<MattressSetting> findListAllMattressSetting(Integer mattressId);

    //get firmness targeted value
    Integer getTargetedValue(Integer mattressId);

    @Modifying
    @Transactional
    Integer updateFavoriteFirmness(Integer mattressSettingId, String firmnessData);

    @Transactional
    @Modifying
    Integer updateSettingStatus(Integer mattressId);
}
