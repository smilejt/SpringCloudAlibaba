package com.abandon.jt.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @createData: 2019-08-02 10:16
 * @author: LongJunTao
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerApplication {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
        logger.info("---------------------Consumer启动成功---------------------");
    }
}
