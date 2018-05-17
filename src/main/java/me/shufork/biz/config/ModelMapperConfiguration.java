package me.shufork.biz.config;

import me.shufork.biz.converter.ApiResourceConverter;
import me.shufork.biz.converter.RoleConverter;
import me.shufork.biz.converter.UiMenuResourceConverter;
import me.shufork.biz.converter.UserConverter;
import me.shufork.biz.domain.ApiResource;
import me.shufork.biz.domain.Role;
import me.shufork.biz.domain.UiMenuResource;
import me.shufork.biz.domain.User;
import me.shufork.biz.dto.ui.ApiAccessDto;
import me.shufork.biz.dto.ui.UiMenuAccessDto;
import me.shufork.common.dto.user.RoleDto;
import me.shufork.common.dto.user.UserAuthDto;
import me.shufork.common.dto.user.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(User.class, UserDto.class).setConverter(new UserConverter.ToUserDto());
        modelMapper.createTypeMap(User.class, UserAuthDto.class).setConverter(new UserConverter.ToUserAuthDto());
        modelMapper.createTypeMap(Role.class, RoleDto.class).setConverter(new RoleConverter.ToRoleDto());
        modelMapper.createTypeMap(UiMenuResource.class, UiMenuAccessDto.class).setConverter(new UiMenuResourceConverter.ToUiMenuAccessDto());
        modelMapper.createTypeMap(ApiResource.class, ApiAccessDto.class).setConverter(new ApiResourceConverter.ToApiAccessDto());

        return modelMapper;
    }
}
