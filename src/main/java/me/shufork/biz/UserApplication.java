package me.shufork.biz;

import me.shufork.common.mq.source.UserActivatedSource;
import me.shufork.common.mq.source.UserCreatedSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"me.shufork.biz", "me.shufork.common"})
@EnableDiscoveryClient
@EnableBinding({UserCreatedSource.class, UserActivatedSource.class})
@EnableJpaRepositories(basePackages = "me.shufork.biz.repository")
@EnableCaching
@EnableSwagger2
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}