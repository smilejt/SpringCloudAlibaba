package com.abandon.jt.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @createData: 2019-08-02 13:55
 * @author: LongJunTao
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FeignApplication {

    private static final Logger logger = LoggerFactory.getLogger(FeignApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class,args);
        logger.info("---------------------Feign启动成功---------------------");
    }
}
