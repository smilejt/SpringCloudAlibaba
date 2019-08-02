package com.abandon.jt.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @createData: 2019-08-02 10:21
 * @author: LongJunTao
 * @Description:
 */
@Configuration
public class NaCosConsumerConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
