package me.shufork.biz.service;

import me.shufork.biz.domain.ApiResource;
import me.shufork.biz.domain.Role;
import me.shufork.biz.domain.RolePermission;
import me.shufork.biz.domain.UiMenuResource;

import java.util.List;

public interface PermissionService {
    List<Role> getRolesByUserId(String uid);

    List<UiMenuResource> getUiMenuResourcesByRoleId(String roleId);

    List<ApiResource> getApiResourcesByRoleId(String roleId);

    List<RolePermission> setRolePermissionsForUser(String uid,Iterable<Role> roles);
    List<RolePermission> removeAllRolePermissionsForUser(String uid);
}
