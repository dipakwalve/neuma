package com.duroflex.neuma.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duroflex.neuma.app.model.ActorRole;
import com.duroflex.neuma.app.model.ERole;



@Repository
public interface RoleRepository extends JpaRepository<ActorRole, Long> {

    Optional<ActorRole> findByName(ERole name);


}

