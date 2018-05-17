package me.shufork.biz.repository;

import me.shufork.biz.domain.ApiPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApiPermissionRepository extends JpaRepository<ApiPermission,String> {
    List<ApiPermission> findAllByRoleId(String roleId);
}
