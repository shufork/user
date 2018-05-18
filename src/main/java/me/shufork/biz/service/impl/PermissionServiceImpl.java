package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.ApiResource;
import me.shufork.biz.domain.Role;
import me.shufork.biz.domain.RolePermission;
import me.shufork.biz.domain.UiMenuResource;
import me.shufork.biz.repository.*;
import me.shufork.biz.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Transactional
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private ApiResourceRepository apiResourceRepository;

    @Autowired
    private ApiPermissionRepository apiPermissionRepository;

    @Autowired
    private UiMenuResourceRepository uiMenuResourceRepository;

    @Autowired
    private UiMenuPermissionRepository uiMenuPermissionRepository;


    @Transactional(readOnly = true)
    @Override
    public List<Role> getRolesByUserId(String uid) {
        List<Role> roles = new LinkedList<>();
        rolePermissionRepository.findAllByUserId(uid).forEach(rolePermission -> {
            roles.add(roleRepository.getOne(rolePermission.getRoleId()));
        });
        return roles;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UiMenuResource> getUiMenuResourcesByRoleId(String roleId) {
        List<UiMenuResource> resources = new LinkedList<>();
        uiMenuPermissionRepository.findAllByRoleId(roleId)
                .forEach( o ->resources.add(uiMenuResourceRepository.findOne(o.getMenuResourceId())));
        return resources;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ApiResource> getApiResourcesByRoleId(String roleId) {
        List<ApiResource> resources = new LinkedList<>();
        apiPermissionRepository.findAllByRoleId(roleId)
                .forEach(o-> resources.add(apiResourceRepository.findOne(o.getApiResourceId())));
        return resources;
    }

    @Override
    public List<RolePermission> setRolePermissionsForUser(String uid, Iterable<Role> roles) {
        removeAllRolePermissionsForUser(uid);
        List<RolePermission> list = new LinkedList<>();
        roles.forEach(role->{
            RolePermission permission =new RolePermission();
            permission.setRoleId(role.getId());
            permission.setUserId(uid);
            list.add(permission);
        });
        return rolePermissionRepository.save(list);
    }

    @Override
    public List<RolePermission> removeAllRolePermissionsForUser(String uid) {
        List<RolePermission> remove = rolePermissionRepository.findAllByUserId(uid);
        rolePermissionRepository.delete(remove);
        return remove;
    }
}
