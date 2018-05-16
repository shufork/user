package me.shufork.biz.cache.dao;

import me.shufork.biz.domain.Role;
import me.shufork.biz.repository.RoleRepository;
import me.shufork.common.constants.CacheNameConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RoleCache {
    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    @CachePut(cacheNames = {CacheNameConstants.ROLE_BY_NAME},key = "'role:name:' + #name" /*,unless = "#result eq null"*/)
    public Role preLoadRole(String name){
        return roleRepository.findRoleByName(name);
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = {CacheNameConstants.ROLE_BY_NAME},key = "'role:name:' + #name" /*,unless = "#result eq null"*/)
    public Role findRoleByName(String name){
        return roleRepository.findRoleByName(name);
    }

    @Transactional(readOnly = false)
    @CacheEvict(cacheNames = {CacheNameConstants.ROLE_BY_NAME},key = "'role:name:' + #role?.name")
    public void deleteRole(Role role){
        roleRepository.delete(role);
    }

    @Transactional(readOnly = false)
    @CachePut(cacheNames = {CacheNameConstants.ROLE_BY_NAME},key = "'role:name:' + #role?.name")
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }
}
