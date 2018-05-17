package me.shufork.biz.repository;

import me.shufork.biz.domain.UiMenuPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UiMenuPermissionRepository extends JpaRepository<UiMenuPermission,String> {
    List<UiMenuPermission> findAllByRoleId(String roleId);
}
