package com.abandon.jt.feign.controller;

import com.abandon.jt.feign.service.EchoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @createData: 2019-08-02 15:13
 * @author: LongJunTao
 * @Description:
 */
@RestController
public class NaCosConsumerFeignController {

    @Resource
    private EchoService echoService;

    @GetMapping(value = "/echo/hi")
    public String echo() {
        return echoService.echo("Feign");
    }
}
