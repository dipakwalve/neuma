package com.duroflex.neuma.app.controller;

import com.duroflex.neuma.app.payload.response.MessageResponse;
import com.duroflex.neuma.app.service.FirmnessLevelSetting;
import com.duroflex.neuma.app.service.MattressSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MattressSettingController {

    Logger log = LoggerFactory.getLogger(MattressSettingController.class);
    @Autowired
    private MattressSettingService mattressSettingService;

    @Autowired
    private FirmnessLevelSetting firmnessLevelSettingService;

    //change in the mattress side
    @PutMapping("/changeSide")
    public ResponseEntity<?> changeMattressSide(@RequestParam Integer userId, String mattressSide) {
        return ResponseEntity.ok().body(mattressSettingService.changeMattressSide(userId, mattressSide));
    }

    //set targetedValue after changing user firmness level
    @PostMapping("/setTargetedValue")
    public ResponseEntity<?> setTargetedValue(short targetedValue, Integer mattressId) {
        Integer result = firmnessLevelSettingService.setFirmnessLevelTargeted(targetedValue, mattressId);
        if(result==0){
            return  ResponseEntity.ok(new MessageResponse("Record Not found"));
        }
        return ResponseEntity.ok(new MessageResponse("Targeted value updated"));
    }

    @GetMapping("/getTargetedValue")
    public ResponseEntity<?> getTargetedValue(Integer mattressId){
        return  ResponseEntity.ok(firmnessLevelSettingService.getTargetedValue(mattressId));
    }

}
