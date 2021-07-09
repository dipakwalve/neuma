package com.duroflex.neuma.app.repository;

import com.duroflex.neuma.app.model.BluetoothConnectivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BluetoothConnectivityRepo extends JpaRepository<BluetoothConnectivity, Long> {

}
