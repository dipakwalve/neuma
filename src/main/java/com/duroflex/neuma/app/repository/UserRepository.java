package com.duroflex.neuma.app.repository;

import com.duroflex.neuma.app.model.ActorDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<ActorDetails, Long> {

    Optional<ActorDetails> findByMobileNo(String mobileNo);

    Boolean getBootstrappingStatus(Integer userId);

    @Modifying
    @Transactional
    Integer updateBootstrappingStatus(Boolean bootstrappingStatus, Integer userId);

    Boolean existsByMobileNo(String mobileNo);

    Boolean existsByEmailId(String email);

    ActorDetails findByUserId(Integer userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE neuma.user_details SET full_name=?2 WHERE user_id=?1", nativeQuery = true)
    Integer updateProfile(Integer userId, String fullName);

    @Transactional
    @Modifying
    Integer updateFullName(Integer userId, String fullName);
}
