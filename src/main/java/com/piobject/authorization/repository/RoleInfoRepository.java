package com.piobject.authorization.repository;

import com.piobject.authorization.model.RoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleInfoRepository extends JpaRepository<RoleInfo, Long> {

    Optional<RoleInfo> findByRoleName(String roleName);
}
