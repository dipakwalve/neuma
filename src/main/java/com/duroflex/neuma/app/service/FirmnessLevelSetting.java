package com.duroflex.neuma.app.service;

import com.duroflex.neuma.app.model.MattressSetting;
import com.duroflex.neuma.app.repository.FirmnessLevelRepository;
import com.duroflex.neuma.app.util.HashMapConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FirmnessLevelSetting {
    private final HashMapConverter converter;
    private final Logger log = LoggerFactory.getLogger(FirmnessLevelSetting.class);
    @Autowired
    private final FirmnessLevelRepository repository;

    @Autowired
    public FirmnessLevelSetting(FirmnessLevelRepository repository, HashMapConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    //Add mattress Setting here
    public MattressSetting addMattressSetting(Map<String, Object> json, Integer mattressId, boolean activeStatus) {
        // If already any mattress Setting id activate the i shall given update here to
        // deactivate firmness level
        MattressSetting msObj = new MattressSetting();
        MattressSetting ms = null;
        try {
            MattressSetting getMatSet = repository.findByMattressSetting(mattressId);
            if (getMatSet != null) {
                repository.updateStatus(getMatSet.getMattressSettingId(), false);
            }
            msObj.setFirmnessData(converter.convertToDatabaseColumn(json));
            msObj.setMattressId(mattressId);
            msObj.setActivateSetting(activeStatus);
            msObj.setSettingActiveStatus(true);
            ms = repository.save(msObj);
        } catch (Exception e) {

            log.error(e.getLocalizedMessage());
        }

        return ms;
    }

    //here get current mattress Setting form mattress id
    public Map<String, Object> getSettingList(Integer mattressId) {

        MattressSetting listMattress = repository.findByMattressSetting(mattressId);
        Map<String, Object> matList = new HashMap<>();
        if (listMattress != null) {
            matList.put("firmnessLevel", converter.convertToEntityAttribute(listMattress.getFirmnessData()));
            matList.put("mattressId", listMattress.getMattressId());
        }

        return matList;
    }

    //Update mattress Details and return updated mattress Setting
    public List<MattressSetting> updateMattress(Map<String, Object> mattressJson, Integer matSettingId) {
        String dbFirmnessData = converter.convertToDatabaseColumn(mattressJson);
        Integer result = repository.updateMattressSetting(dbFirmnessData, matSettingId);
        List<MattressSetting> setting = null;
        if (result > 0) {
            setting = repository.findById(matSettingId);
        }
        return setting;
    }

    //use to reset firmness level
    public Map<String, Object> getFirmnessSetting(Integer matSettingId) {
        Map<String, Object> map = new HashMap<>();

        MattressSetting setting = repository.findByMattressSetting(matSettingId);
        MattressSetting mattressSetting = null;
        Optional<MattressSetting> objPrevData = getPreviousMattress(setting.getMattressId(), setting.getUpdatedAt());
        System.out.println(objPrevData);
        boolean status = activateMattressSetting(setting.getMattressSettingId(), false);
        if (objPrevData.isPresent()) {
            System.out.println("Status : " + objPrevData.get().getFirmnessData() + " " + objPrevData.get().getUpdatedAt());
            if (activateMattressSetting(objPrevData.get().getMattressSettingId(), true)) {
                mattressSetting = repository.findByMattressSetting(objPrevData.get().getMattressId());
                System.out.println("Mattress Settings  : " + mattressSetting);
                map.put("firmnessLevel", converter.convertToEntityAttribute(mattressSetting.getFirmnessData()));
                map.put("mattressId", mattressSetting.getMattressId());
            }
        } else {
            map.put("message", "Not previous Setting found");
        }


        return map;
    }

    private Optional<MattressSetting> getPreviousMattress(Integer mattressId, Date updatedAt) {

        List<MattressSetting> listAllSetting = repository.orderByMattressId(mattressId);
        Optional<MattressSetting> getPreviousSetting = listAllSetting.stream().filter(ms -> ms.getUpdatedAt().before(updatedAt)).findFirst();
        return getPreviousSetting;
    }

    private Boolean activateMattressSetting(Integer mattressSettingId, boolean status) {
        int i = repository.updateStatus(mattressSettingId, status);
        return i >= 1;
    }

    public Map<String, Object> changeFavoriteFirmness(Integer mattressId, Integer favoritesFirmness) {

//        Integer status = repository.updateFavoriteFirmness(mattressId,favoritesFirmness);

        List<MattressSetting> listOFMattress = repository.findByMattressId(mattressId);
        log.info("MATTRESS ID " + mattressId + " " + favoritesFirmness);
        Map<String, Object> firmnessdata = new HashMap<>();
        Iterator itr = listOFMattress.iterator();
        while (itr.hasNext()) {
            MattressSetting objMat = (MattressSetting) itr.next();
            firmnessdata = converter.convertToEntityAttribute(objMat.getFirmnessData());
            firmnessdata.put("favorite", favoritesFirmness);
            repository.updateFavoriteFirmness(objMat.getMattressSettingId(), converter.convertToDatabaseColumn(firmnessdata));
        }
        System.out.println(firmnessdata);
        return firmnessdata;
    }

    public Integer setFirmnessLevelTargeted(short targetedValue, Integer mattressId) {
        Integer result = 0;
        try {
            result = repository.setTargetedValue(targetedValue, mattressId);
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
        return result;
    }

    public Integer getTargetedValue(Integer mattressId) {
        Integer targetedValue = repository.getTargetedValue(mattressId);
        return targetedValue;
    }

    public String updateSettingStatus(Integer mattressId) {
        Integer status = repository.updateSettingStatus(mattressId);
        if ((status == 0)) {
            return "Something went wrong ";
        } else {
            return "Setting updated";

        }
    }
}
