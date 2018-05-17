package me.shufork.biz.converter;

import me.shufork.biz.domain.UiMenuResource;
import me.shufork.biz.dto.ui.UiMenuAccessDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class UiMenuResourceConverter {
    public static class ToUiMenuAccessDto implements Converter<UiMenuResource,UiMenuAccessDto> {
        @Override
        public UiMenuAccessDto convert(MappingContext<UiMenuResource, UiMenuAccessDto> context) {
            UiMenuResource source = context.getSource();
            UiMenuAccessDto target = new UiMenuAccessDto();
            target.setRef(source.getId());
            target.setName(source.getUiText());
            target.setRoute(source.getUiPage());
            target.setId(source.getUiNode());
            target.setParentId(source.getUiParentNode());
            return target;
        }
    }
}
