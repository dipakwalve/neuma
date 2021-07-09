package com.duroflex.neuma.app.repository;

import com.duroflex.neuma.app.model.MattressDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface MattressSettingRepository extends JpaRepository<MattressDetails, Long> {
    MattressDetails findByUserId(Integer userId);

    MattressDetails findByMattressIdentifier(String mattressIdentifier, Integer userId);

    Optional<MattressDetails> findByMattressIdentifierAndSide(String mattressIdentifier, String mattressSide);


    List<MattressDetails> findMattressIdentifier(String mattressIdentifier);

    @Modifying
    @Transactional
    Integer updateSideDetails(Integer mattressId, String mattressSide);

    @Modifying
    @Transactional
    Integer modifyMattressSideNames(Integer mattressId, Integer userId, String mattressSideName);

    Integer countMattressIdentifier(String mattressIdentifier);
}
