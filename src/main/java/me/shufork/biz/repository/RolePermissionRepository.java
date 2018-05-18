package me.shufork.biz.repository;

import me.shufork.biz.domain.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission,String>{
    List<RolePermission> findAllByUserId(String userId);
    RolePermission findOneByUserIdAndRoleId(String userId,String roleId);
}
