package me.shufork.biz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.dto.ui.ApiAccessDto;
import me.shufork.biz.dto.ui.UiMenuAccessDto;
import me.shufork.biz.service.UiAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Api(value = "/ui-ac", description = "UI访问控制API")
public class UiAccessController {

    @Autowired
    private UiAccessService uiAccessService;

    @RequestMapping(value = "/ui-ac/menus", method = RequestMethod.GET)
    @ApiOperation(value = "菜单列表")
    public List<UiMenuAccessDto> getMenusAccessList(
            @ApiParam(name = "user-id", value = "用户UID", required = true) @RequestParam(name="user-id") String userId){
        return uiAccessService.getMenusAccessList(userId);
    }
    @RequestMapping(value = "/ui-ac/api-entries", method = RequestMethod.GET)
    @ApiOperation(value = "API列表")
    public List<ApiAccessDto> getApiAccessList(
            @ApiParam(name = "user-id", value = "用户UID", required = true) @RequestParam(name="user-id") String userId){
        return uiAccessService.getApiAccessList(userId);
    }
}
