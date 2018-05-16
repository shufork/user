package me.shufork.biz.repository;

import me.shufork.biz.domain.ApiPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiPermissionRepository extends JpaRepository<ApiPermission,String> {
}
