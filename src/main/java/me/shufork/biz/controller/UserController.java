package me.shufork.biz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.UserService;
import me.shufork.common.dto.user.CreateUserDto;
import me.shufork.common.dto.user.UserAuthDto;
import me.shufork.common.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@Api(value = "/users", description = "用户API")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "创建用户")
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public UserDto createUser(
            @ApiParam(name = "user", value = "新用户信息", required = true) @Valid @RequestBody CreateUserDto createUserDto){
        return userService.createUser(createUserDto);
    }

    @ApiOperation(value = "激活用户")
    @RequestMapping(value = "/users/active", method = RequestMethod.POST)
    public void activateUser(@ApiParam(name = "user-id", value = "用户UID", required = true) @RequestParam(name="user-id") String userId){
        userService.activateUser(userId);
    }

    @ApiOperation(value = "取用户信息")
    @RequestMapping(value = "/users/uid", method = RequestMethod.GET)
    public UserDto loadUserByUserId(@ApiParam(name = "user-id", value = "用户UID", required = true) @RequestParam(name="user-id") String userId){
        return userService.getUserById(userId);
    }

    @ApiOperation(value = "取用户信息")
    @RequestMapping(value = "/users/name", method = RequestMethod.GET)
    public UserDto loadUserByLoginName(@ApiParam(name = "login-name", value = "用户登陆名称", required = true) @RequestParam(name="login-name") String loginName){
        return userService.getUserByLoginName(loginName);
    }


    @ApiOperation(value = "取用户认证信息")
    @RequestMapping(value = "/users/auth/name", method = RequestMethod.GET)
    public UserAuthDto loadUserAuthByLoginName(@ApiParam(name = "login-name", value = "用户登陆名称", required = true) @RequestParam(name="login-name") String loginName){
        return userService.getUserAuthByLoginName(loginName);
    }
    @RequestMapping(value = "/users/grant", method = RequestMethod.PUT)
    @ApiOperation(value = "重置用户权限")
    public void grantAuthorities(
            @ApiParam(name = "login-name", value = "用户登陆名称", required = true) @RequestParam(name="login-name") String loginName,
            @ApiParam(name = "roles-names", value = "要赋予的权限", required = true) @Valid @RequestBody List<String> roleNames) {
        userService.setUserRoles(loginName, roleNames);
    }
}
