package me.shufork.biz;

import me.shufork.biz.config.ModelMapperConfiguration;
import me.shufork.biz.domain.Role;
import me.shufork.biz.domain.RolePermission;
import me.shufork.biz.domain.User;
import me.shufork.common.dto.user.UserDto;
import me.shufork.common.enums.UserStatusEnums;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;




@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {ModelMapperConfiguration.class},
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        properties={
                "spring.cloud.config.discovery.enabled=false",
                "liquibase.enabled=false"
        })
public class ModelMapperTests {
    //@MockBean
    //private RemoteService remoteService;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void exampleTest() {
        // RemoteService has been injected into the reverser bean
        //given(this.remoteService.someCall()).willReturn("mock");
        //String reverse = reverser.reverseSomeCall();
        //assertThat(reverse).isEqualTo("kcom");

        final Date dateNow = new Date();

        final Role role = new Role();
        role.setCreateBy("system");
        role.setCreatedTime(dateNow);
        role.setName("admin");
        role.setId("role-1");

        final RolePermission rolePermission = new RolePermission();
        rolePermission.setId("user-authority-1");
        rolePermission.setRole(role.getId());

        final User user = new User();
        user.setId("user-1");
        user.setCreatedTime(dateNow);
        user.setDisplayName("john");
        user.setEmail("john@gmail.com");
        user.setLoginName("bobo");
        user.setPassword("111");
        user.setStatus(UserStatusEnums.LOCKED);
        user.setCellPhoneNumber("18012345678");

        final UserDto userDto = modelMapper.map(user,UserDto.class);
        assertThat(userDto.getId()).isEqualTo(user.getId());
        assertThat(userDto.getCellPhoneNumber()).isEqualTo(user.getCellPhoneNumber());
        assertThat(userDto.getDisplayName()).isEqualTo(user.getDisplayName());
        assertThat(userDto.getEmail()).isEqualTo(user.getEmail());
        assertThat(userDto.getLoginName()).isEqualTo(user.getLoginName());
        assertThat(userDto.getPassword()).isEqualTo(user.getPassword());


    }
}
