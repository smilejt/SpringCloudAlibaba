package com.abandon.jt.auth;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @createData: 2019-07-04 13:51
 * @author: LongJunTao
 * @Description:
 */
@EnableSwagger2
@SpringBootApplication
@NacosPropertySource(dataId = "redis", autoRefreshed = true)
@EnableDiscoveryClient
public class AuthApplication {

    private static final Logger logger = LoggerFactory.getLogger(AuthApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
        logger.info("---------------------Auth登陆验证启动成功---------------------");
    }
}
