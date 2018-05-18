package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.dto.ui.ApiAccessDto;
import me.shufork.biz.dto.ui.UiMenuAccessDto;
import me.shufork.biz.service.PermissionService;
import me.shufork.biz.service.UiAccessService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UiAccessServiceImpl implements UiAccessService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PermissionService permissionService;
    @Override
    public List<UiMenuAccessDto> getMenusAccessList(String uid) {
        List<UiMenuAccessDto> list = new LinkedList<>();
        permissionService.getRolesByUserId(uid).forEach(role -> {
            list.addAll(permissionService.getUiMenuResourcesByRoleId(role.getId())
                    .stream().map(o->modelMapper.map(o,UiMenuAccessDto.class) )
                    .collect(Collectors.toList()));
        });
        return list;
    }

    @Override
    public List<ApiAccessDto> getApiAccessList(String uid) {
        List<ApiAccessDto> list = new LinkedList<>();
        permissionService.getRolesByUserId(uid).forEach(role -> {
            list.addAll(permissionService.getApiResourcesByRoleId(role.getId())
                    .stream().map(o->modelMapper.map(o,ApiAccessDto.class) )
                    .collect(Collectors.toList()));
        });
        return list;
    }
}
