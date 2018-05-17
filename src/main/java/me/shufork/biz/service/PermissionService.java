package me.shufork.biz.service;

import me.shufork.biz.domain.ApiResource;
import me.shufork.biz.domain.Role;
import me.shufork.biz.domain.UiMenuResource;

import java.util.List;

public interface PermissionService {
    List<Role> getRolesByUser(String uid);

    List<UiMenuResource> getUiMenuResourcesByRole(String roleId);

    List<ApiResource> getApiResourcesByRole(String roleId);
}
