package me.shufork.biz.cache.dao;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Component;

@Component
@CacheConfig(cacheNames = {"role-authority"})
public class RoleAuthorityDao {
}
