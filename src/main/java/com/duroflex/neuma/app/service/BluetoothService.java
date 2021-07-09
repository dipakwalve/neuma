package com.duroflex.neuma.app.service;

import com.duroflex.neuma.app.model.BluetoothConnectivity;
import com.duroflex.neuma.app.repository.BluetoothConnectivityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BluetoothService {

    @Autowired
    private BluetoothConnectivityRepo connectivityRepo;

    public Map<String, Object> updateStatus(BluetoothConnectivity connectivity) {

        BluetoothConnectivity conn = connectivityRepo.save(connectivity);
        Map<String, Object> result = new HashMap<>();

        try {

            result.put("userId", conn.getUserId());
            result.put("PairedStatus", conn.isPairedStatus());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
