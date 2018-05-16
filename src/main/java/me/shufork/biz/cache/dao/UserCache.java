package me.shufork.biz.cache.dao;


import me.shufork.biz.domain.User;
import me.shufork.biz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@CacheConfig(cacheNames = {"user"})
public class UserCache {

    /*@Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Cacheable(key = "'user:login-name:' + #loginName")
    public User findUserByLoginName(String loginName){
        return userRepository.findUserByLoginName(loginName);
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "'user:id:' + #userId")
    public User findOne(String userId){
        return userRepository.findOne(userId);
    }
    @Transactional(readOnly = false)
    @CachePut(key = "'user:login-name:' + #user.loginName")
    public User save(User user){
        return userRepository.save(user);
    }*/
}
