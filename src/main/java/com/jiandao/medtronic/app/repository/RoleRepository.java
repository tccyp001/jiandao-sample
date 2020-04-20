package com.jiandao.medtronic.app.repository;

import com.jiandao.medtronic.app.model.Role;
import com.jiandao.medtronic.app.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(RoleName roleName);
}
