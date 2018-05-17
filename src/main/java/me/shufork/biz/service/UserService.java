package me.shufork.biz.service;

import me.shufork.biz.exception.UserExistsException;
import me.shufork.common.dto.user.CreateUserDto;
import me.shufork.common.dto.user.RoleDto;
import me.shufork.common.dto.user.UserAuthDto;
import me.shufork.common.dto.user.UserDto;
import me.shufork.common.exceptions.RecordNotFoundException;

import java.util.List;

public interface UserService {

    /**
     * 创建新用户
     * @param createUserDto
     * @return
     * @throws UserExistsException
     */
    UserDto createUser(CreateUserDto createUserDto);


    /**
     * 激活用户
     * @param userId
     * @return
     * @throws RecordNotFoundException
     */
    UserDto activateUser(String userId);


    /**
     * 根据ID获取用户
     * @param userId
     * @return
     * @throws RecordNotFoundException
     */
    UserDto getUserById(String userId);

    /**
     * 根据登录名获取用户
     * @param loginName
     * @return
     */
    UserDto getUserByLoginName(String loginName);

    /**
     * 根据登录名获取用户认证信息
     * @param loginName
     * @return
     */
    UserAuthDto getUserAuthByLoginName(String loginName);

    /**
     * 设置用户权限(角色)
     * @param loginName
     * @param roleNameList
     * @return
     * @throws RecordNotFoundException user/role not found
     */
    UserDto setUserRoles(String loginName, List<String> roleNameList);

    /**
     * 根据角色名查找角色
     * @param roleName
     * @return
     */
    RoleDto findRoleByRoleName(String roleName);


    /**
     * 根据角色名查找角色(忽略未找到的记录)
     * @param roleNames
     * @return
     */
    List<RoleDto> findRoleByRoleName(Iterable<String> roleNames);
}
