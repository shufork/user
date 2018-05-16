package me.shufork.biz.config;

import me.shufork.biz.domain.User;
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

        modelMapper.createTypeMap(User.class, UserDto.class).setConverter(context -> {
            User source = context.getSource();
            UserDto target = new UserDto();

            target.setId(source.getId());
            target.setLoginName(source.getLoginName());
            target.setPassword(source.getPassword());
            target.setDisplayName(source.getDisplayName());
            target.setEmail(source.getEmail());
            target.setCellPhoneNumber(source.getCellPhoneNumber());

            /*List<UserAuthorityDto> authorityDtoList = new ArrayList<>();
            source.getAuthorities().forEach(
                    authority -> {
                        UserAuthorityDto authorityDto = new UserAuthorityDto();
                        authorityDto.setAuthority(authority.getRole().getName());
                        authorityDtoList.add(authorityDto);
                    }
            );
            target.setAuthorities(authorityDtoList);*/

            return target;
        });

        return modelMapper;
    }
}
