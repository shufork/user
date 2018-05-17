package me.shufork.biz.service;

import me.shufork.biz.dto.ui.ApiAccessDto;
import me.shufork.biz.dto.ui.UiMenuAccessDto;

import java.util.List;

public interface UiAccessService {
    List<UiMenuAccessDto> getMenusAccessList(String uid);

    List<ApiAccessDto> getApiAccessList(String uid);
}
