package com.duroflex.neuma.app.controller;

import com.duroflex.neuma.app.model.BluetoothConnectivity;
import com.duroflex.neuma.app.model.MattressDetails;
import com.duroflex.neuma.app.model.MattressSetting;
import com.duroflex.neuma.app.payload.response.MessageResponse;
import com.duroflex.neuma.app.payload.response.PlainMessageResponse;
import com.duroflex.neuma.app.service.ActorService;
import com.duroflex.neuma.app.service.BluetoothService;
import com.duroflex.neuma.app.service.FirmnessLevelSetting;
import com.duroflex.neuma.app.service.MattressSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class AppSettingController {

    Logger log = LoggerFactory.getLogger(AppSettingController.class);

    @Autowired
    private BluetoothService bluetoothService;
    @Autowired
    private MattressSettingService settingService;
    @Autowired
    private FirmnessLevelSetting setting;
    @Autowired
    private ActorService actorService;


    @PutMapping("/updateBluetoothStatus")
    public ResponseEntity<Map<String, Object>> updateBluetoothStatus(@RequestBody BluetoothConnectivity bluetoothConnectivity) {
        return ResponseEntity.ok(bluetoothService.updateStatus(bluetoothConnectivity));

    }

    //get mattress side list
    @PostMapping("/getMattressSide")
    public ResponseEntity<Object> selectMattressSide(@RequestParam String mattressId, Integer userId) {

        Map<String, String> objMap = new HashMap<>();

        String settingObj = settingService.getOccupySideList(mattressId, userId);
        if (settingObj.isEmpty()) {
            return ResponseEntity.ok(new MessageResponse("Mattress Not found ", userId));
        } else {
            objMap.put("MattressSide", settingObj);
            objMap.put("userId", userId.toString());
            return ResponseEntity.ok().body(objMap);

        }

    }

    //Add new Mattress
    @PostMapping("/addMattressSide")
    public ResponseEntity<MattressDetails> addMattress(@RequestBody MattressDetails entity) {
        return ResponseEntity.ok(settingService.addMattress(entity));

    }

    //Add new mattress setting
    @PostMapping("/addMattressSetting")
    public ResponseEntity<MattressSetting> addMattressSetting(@RequestBody Map<String, Object> json, Integer mattressId,
                                                              boolean activateSetting) {
        return ResponseEntity.ok(setting.addMattressSetting(json, mattressId, activateSetting));

    }

    @PostMapping("/getMattressSetting")
    public ResponseEntity<Map<String, Object>> getMattressSetting(@RequestParam Integer mattressId) {
        return ResponseEntity.ok(setting.getSettingList(mattressId));

    }

    @PutMapping("/getSettingStatus")
    public ResponseEntity<PlainMessageResponse> getSettingStatus(@RequestParam Integer mattressId) {
        return ResponseEntity.ok(new PlainMessageResponse(setting.updateSettingStatus(mattressId)));

    }

    //here Update mattress Setting
    @PutMapping("/updateMattress")
    public ResponseEntity<List<MattressSetting>> updateMattress(@RequestBody Map<String, Object> json, Integer mattressId) {

        return ResponseEntity.ok(setting.updateMattress(json, mattressId));
    }

    /**
     * @param userId
     * @return
     */
    @PostMapping("/getProfile")
    public ResponseEntity<Map<String, Object>> getProfiles(Integer userId) {

        return ResponseEntity.ok(actorService.findById(userId));
    }

    //
    @PutMapping("/updateUserProfile")
    public ResponseEntity<Map<String, Object>> updateUserProfile(@RequestParam Integer userId, @RequestParam String fullName) {

        return ResponseEntity.ok(actorService.updateProfile(userId, fullName));
    }

    //reset firmnesslevel from 100 to 10 order only
    @PutMapping("/resetFirmness")
    public ResponseEntity<Map<String, Object>> resetFirmnessLevel(@RequestParam Integer mattressId) {

        return ResponseEntity.ok(setting.getFirmnessSetting(mattressId));

    }

    @PutMapping("/changeFavoriteFirmness")
    public ResponseEntity<Map<String, Object>> changeFavoriteFirmness(@RequestParam Integer mattressId, Integer favoritesFirmness) {
        return ResponseEntity.ok(setting.changeFavoriteFirmness(mattressId, favoritesFirmness));
    }

    // Set the mattress Side name
    @GetMapping("/getSideNames")
    public ResponseEntity<Optional<MattressDetails>> getSideNames(@RequestParam String mattressIdentifier, String mattressSide, Integer userId) {
        return ResponseEntity.ok(settingService.getMattressSideNames(mattressIdentifier, mattressSide, userId));
    }

    //count one mattress allocate how many user
    @GetMapping("/countMattressUser")
    public ResponseEntity<PlainMessageResponse> countMattressIdentifire(String mattressIdentifier) {
        Integer count = settingService.countMattressUser(mattressIdentifier);
        return ResponseEntity.ok(new PlainMessageResponse(count.toString()));
    }

    @PutMapping("/changeSideName")
    public ResponseEntity<PlainMessageResponse> modifySideName(@RequestParam(required = false) Integer userId, Integer mattressId, String mattressSideName) {
        String result = settingService.changeMattressSide(userId, mattressId, mattressSideName);
        return ResponseEntity.ok(new PlainMessageResponse(result));
    }

}
