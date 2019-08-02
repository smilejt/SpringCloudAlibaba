package com.abandon.jt.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @createData: 2019-08-02 10:23
 * @author: LongJunTao
 * @Description:
 */
@RestController
public class ConsumerController {

    @Resource
    private LoadBalancerClient loadBalancerClient;

    @Resource
    private RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping(value = "/echo/app/name")
    public String echo(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-feign");
        String url = String.format("http://%s:%s/echo/hi",serviceInstance.getHost(),serviceInstance.getPort());
        return restTemplate.getForObject(url,String.class);
    }
}
