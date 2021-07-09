package com.duroflex.neuma.app.repository;

import com.duroflex.neuma.app.model.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends JpaRepository<Cities, Long> {

}
