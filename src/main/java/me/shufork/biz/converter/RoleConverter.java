package me.shufork.biz.converter;

import me.shufork.biz.domain.Role;
import me.shufork.common.dto.user.RoleDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public abstract class RoleConverter {
    public static class ToRoleDto implements Converter<Role,RoleDto>{

        @Override
        public RoleDto convert(MappingContext<Role, RoleDto> context) {
            Role source = context.getSource();
            RoleDto target = new RoleDto();
            target.setId(source.getId());
            target.setName(source.getName());
            target.setType(source.getType().toString());
            target.setEnabled(source.getEnabled());
            target.setDescription(source.getDescription());
            target.setCreateBy(source.getCreateBy());

            return target;
        }
    }
}
