package me.shufork.biz.converter;

import me.shufork.biz.domain.User;
import me.shufork.common.dto.user.UserAuthDto;
import me.shufork.common.dto.user.UserDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.Collections;

public abstract class UserConverter {
    public static class ToUserDto implements Converter<User,UserDto>{

        @Override
        public UserDto convert(MappingContext<User, UserDto> context) {
            User source = context.getSource();
            UserDto target = new UserDto();

            target.setId(source.getId());
            target.setLoginName(source.getLoginName());
            target.setStatus(source.getStatus().toString());
            target.setDisplayName(source.getDisplayName());
            target.setEmail(source.getEmail());
            target.setMobile(source.getMobile());
            return target;
        }
    }
    public static class ToUserAuthDto implements Converter<User,UserAuthDto>{

        @Override
        public UserAuthDto convert(MappingContext<User, UserAuthDto> context) {
            User source = context.getSource();
            UserAuthDto target = new UserAuthDto();

            target.setId(source.getId());
            target.setLoginName(source.getLoginName());
            target.setStatus(source.getStatus().toString());
            target.setPassword(source.getPassword());
            target.setDisplayName(source.getDisplayName());
            target.setEmail(source.getEmail());
            target.setMobile(source.getMobile());

            // init roles with empty list
            target.setRoles(Collections.emptyList());
            return target;
        }
    }
}
