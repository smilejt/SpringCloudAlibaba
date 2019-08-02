package com.abandon.jt.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @createData: 2019-08-02 15:14
 * @author: LongJunTao
 * @Description:
 */
@FeignClient(value = "nacos-provider")
public interface EchoService {

    /**
     * Demo方法
     * @param message Demo参数
     * @return
     */
    @GetMapping(value = "/echo/{message}")
    String echo(@PathVariable("message") String message);
}
