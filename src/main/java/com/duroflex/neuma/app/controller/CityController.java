package com.duroflex.neuma.app.controller;

import com.duroflex.neuma.app.model.Cities;
import com.duroflex.neuma.app.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {

    @Autowired
    private CitiesService citiesService;

    @GetMapping("/cityList")
    public ResponseEntity<List<Cities>> getCityList() {

        return ResponseEntity.ok(citiesService.getCityList());
    }

}
