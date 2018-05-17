package me.shufork.biz.converter;

import me.shufork.biz.domain.ApiResource;
import me.shufork.biz.dto.ui.ApiAccessDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public abstract class ApiResourceConverter {
    public static class ToApiAccessDto implements Converter<ApiResource,ApiAccessDto>{
        @Override
        public ApiAccessDto convert(MappingContext<ApiResource, ApiAccessDto> context) {
            ApiResource source = context.getSource();
            ApiAccessDto target = new ApiAccessDto();
            target.setId(source.getId());
            target.setApiName(source.getScope()+":"+source.getName());
            target.setApiMethod(source.getMethod().toString());
            target.setApiPath(source.getPath());

            return target;
        }
    }
}
