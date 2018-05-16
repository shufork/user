package me.shufork.biz.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.UserService;
import me.shufork.common.dto.user.RoleDto;
import me.shufork.common.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(value = "/roles", description = "角色API")
public class RoleController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "取角色信息")
    @RequestMapping(value = "/roles/name", method = RequestMethod.GET)
    public RoleDto loadUserByLoginName(@ApiParam(name = "name", value = "角色名称", required = true) @RequestParam(name="name") String name){
        return userService.findRoleByRoleName(name);
    }
}
