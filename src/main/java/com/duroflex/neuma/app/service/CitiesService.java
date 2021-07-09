package com.duroflex.neuma.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duroflex.neuma.app.model.Cities;
import com.duroflex.neuma.app.repository.CitiesRepository;

@Service
public class CitiesService {

    @Autowired
    private CitiesRepository citiesRepository;

    public List<Cities> getCityList() {
        List<Cities> cityList = null;
        cityList = citiesRepository.findAll();
        if (!(cityList.isEmpty()))
            return cityList;
        else
            return cityList;
    }

}
