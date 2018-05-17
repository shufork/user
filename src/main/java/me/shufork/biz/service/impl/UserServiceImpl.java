package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.cache.dao.RoleCache;
import me.shufork.biz.constants.BuildInRoleNames;
import me.shufork.biz.domain.Role;
import me.shufork.biz.domain.RolePermission;
import me.shufork.biz.domain.User;
import me.shufork.biz.repository.RolePermissionRepository;
import me.shufork.biz.repository.RoleRepository;
import me.shufork.biz.repository.UserRepository;
import me.shufork.biz.service.UserService;
import me.shufork.common.constants.CacheNameConstants;
import me.shufork.common.dto.user.CreateUserDto;
import me.shufork.common.dto.user.RoleDto;
import me.shufork.common.dto.user.UserAuthDto;
import me.shufork.common.dto.user.UserDto;
import me.shufork.common.enums.UserStatusEnums;
import me.shufork.common.exceptions.RecordNotFoundException;
import me.shufork.common.mq.payload.UserActivatePayload;
import me.shufork.common.mq.source.UserActivatedSource;
import me.shufork.common.mq.source.UserCreatedSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Transactional
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    /*@Autowired
    private RoleRepository roleRepository;*/

    @Autowired
    private RoleCache roleCache;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Self-autowired reference to proxified bean of this class.
     */
    //@Resource
    //private UserService self;


    @Autowired
    @Qualifier("userPasswordEncoder")
    private  PasswordEncoder userPasswordEncoder;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserCreatedSource userCreatedSource;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserActivatedSource userActivatedSource;

    private final AtomicReference<Role> defaultRole=new AtomicReference<>(null);

    //@PostConstruct
    private void afterConstruct(){
        loadDefaultRole();
    }

    public void loadDefaultRole(){
        Role role = roleCache.preLoadRole(BuildInRoleNames.ROLE_USER.getValue());
        if(role == null){
            log.error("can not load default role({}) for new user,check database init data ",
                    BuildInRoleNames.ROLE_USER.getValue());
        }else{
            log.info("load default role({}) for new user",BuildInRoleNames.ROLE_USER.getValue());
        }
        defaultRole.set(role);
    }


    @Caching(put = {
            @CachePut(cacheNames = {CacheNameConstants.USER_DTO_BY_LOGIN_NAME},key = "'user:login-name:' + #createUserDto.loginName"),
            @CachePut(cacheNames = {CacheNameConstants.USER_DTO_BY_ID},key = "'user:login-name:' + #result.id")
    })
    @Override
    public UserDto createUser(CreateUserDto createUserDto) {
        /*Optional.ofNullable(userRepository.findUserByLoginName(createUserDto.getLoginName()))
                .ifPresent(user -> {throw new UserExistsException(user.getLoginName());});

        final RolePermission rolePermission = new RolePermission();
        rolePermission.setRole(defaultRole.get());

        final User user = modelMapper.map(createUserDto,User.class);

        user.setStatus(UserStatus.CREATED);
        user.setPassword(userPasswordEncoder.encode(createUserDto.getPassword()));
        user.addRoleAuthority(rolePermission);

        userRepository.save(user);
        assert user.getId() != null;

        final UserCreatedPayload payload = modelMapper.map(user,UserCreatedPayload.class);
        payload.setUserId(user.getId());
        Message<UserCreatedPayload> message = MessageBuilder.withPayload(payload).build();

        userCreatedSource.output().send(message);

        return modelMapper.map(user,UserDto.class);*/
        return null;
    }

    @Caching(put = {
            @CachePut(cacheNames = {CacheNameConstants.USER_DTO_BY_LOGIN_NAME},key = "'user:login-name:' + #result?.loginName"),
            @CachePut(cacheNames = {CacheNameConstants.USER_DTO_BY_ID},key = "'user:login-name:' + #result?.id")
    })
    @Override
    public UserDto activateUser(String userId) {
        User user =  Optional.ofNullable(userRepository.findOne(userId))
                .orElseThrow (()->new RecordNotFoundException("user id",userId));

        UserDto userDto = null;
        if(user.getStatus() == UserStatusEnums.ACTIVE){
            return userDto;
        }
        user.setStatus(UserStatusEnums.ACTIVE);
        userDto = modelMapper.map(userRepository.save(user),UserDto.class);

        final UserActivatePayload payload = new UserActivatePayload();
        payload.setUserId(user.getId());
        Message<UserActivatePayload> message = MessageBuilder.withPayload(payload).build();
        userActivatedSource.output().send(message);
        return userDto;
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = {CacheNameConstants.USER_DTO_BY_ID},key = "'user:id:' + #userId")
    @Override
    public UserDto getUserById(String userId) {
        User user =  Optional.ofNullable(userRepository.findOne(userId))
                .orElseThrow (()->new RecordNotFoundException("user id",userId));
        return modelMapper.map(user,UserDto.class);
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = {CacheNameConstants.USER_DTO_BY_LOGIN_NAME},key = "'user:login-name:' + #loginName")
    @Override
    public UserDto getUserByLoginName(String loginName) {
        User user =  Optional.ofNullable(userRepository.findUserByLoginName(loginName))
                .orElseThrow (()->new RecordNotFoundException("user name",loginName));
        return modelMapper.map(user,UserDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public UserAuthDto getUserAuthByLoginName(String loginName) {
        User user =  Optional.ofNullable(userRepository.findUserByLoginName(loginName))
                .orElseThrow (()->new RecordNotFoundException("user name",loginName));
        UserAuthDto userAuthDto =  modelMapper.map(user,UserAuthDto.class);
        List<RolePermission> rolePermissions = rolePermissionRepository.findAllByUserId(user.getId());
        List<Role> roles = roleRepository.findAll(rolePermissions.stream().map(o->o.getRoleId()).collect(Collectors.toList()));
        userAuthDto.setRoles(roles.stream().map(o->modelMapper.map(o,RoleDto.class)).collect(Collectors.toList()));
        return userAuthDto;
    }


    @CachePut(cacheNames = {CacheNameConstants.USER_DTO_BY_LOGIN_NAME},key = "'user:login-name:' + #loginName")
    @Override
    public UserDto setUserRoles(String loginName, List<String> roleNameList) {
        User user =  Optional.ofNullable(userRepository.findUserByLoginName (loginName))
                .orElseThrow (()->new RecordNotFoundException("user name",loginName));

        return setUserRoles(user,roleNameList);

    }

    @Transactional(readOnly = true)
    @Override
    public RoleDto findRoleByRoleName(String roleName) {
        Role role = roleCache.findRoleByName(roleName);
        return role == null?null:modelMapper.map(role,RoleDto.class);
    }


    @Override
    @Transactional(readOnly = true)
    public List<RoleDto> findRoleByRoleName(Iterable<String> roleNames) {
        return fetchRoles(roleNames).stream().map( role -> modelMapper.map(role,RoleDto.class)).collect(Collectors.toList());
    }

    private List<Role> fetchRoles(Iterable<String> roleNames){
        List<Role> r = new ArrayList<>();
        roleNames.forEach(roleName -> {
            Optional.ofNullable(roleCache.findRoleByName(roleName)).ifPresent(role ->{
                r.add(role);
            });
        });
        return r;
    }


    @Caching(
            evict = {
                    @CacheEvict(cacheNames = {CacheNameConstants.USER_DTO_BY_LOGIN_NAME},key = "'user:login-name:' + #user?.loginName",condition = "#user != null"),
                    @CacheEvict(cacheNames = {CacheNameConstants.USER_DTO_BY_ID},key = "'user:id:' + #user?.id",condition = "#user != null")
            }
    )
    public UserDto setUserRoles(@NotNull User user, List<String> roleNameList) {
        // ignore user status check
        /*

                roleNameList = roleNameList.stream().distinct().collect(Collectors.toList());
                List<Role> roleList = fetchRoles(roleNameList);
                if(roleList.size() != roleNameList.size()){
                    Set<String> loaded = roleList.stream().map(o->o.getName()).collect(Collectors.toSet());
                    List<String> leaked = roleNameList.stream().filter(o-> loaded.equals(o)).collect(Collectors.toList());
                    throw new RecordNotFoundException("role",String.join(",",leaked));
                }
                List<RolePermission> authorities = user.getAuthorities();
                if(authorities.size() >0){
                    rolePermissionRepository.delete(authorities);
                    user.setAuthorities(null);
                }

                roleList.forEach(role -> {
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRole(role);
                    user.addRoleAuthority(rolePermission);
                });
                return modelMapper.map(userRepository.save(user),UserDto.class);
        */
        return null;

    }

    @Component
    static class Initializer implements ApplicationListener<ApplicationReadyEvent> {
        @Autowired
        private UserServiceImpl userService;
        @Override
        public void onApplicationEvent(ApplicationReadyEvent event) {
            userService.loadDefaultRole();
        }
    }
}
