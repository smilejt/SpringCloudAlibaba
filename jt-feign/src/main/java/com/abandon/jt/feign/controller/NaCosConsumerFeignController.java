package com.abandon.jt.feign.controller;

import com.abandon.jt.feign.service.EchoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @createData: 2019-08-02 15:13
 * @author: LongJunTao
 * @Description:
 */
@RestController
public class NaCosConsumerFeignController {

    private static final Logger logger = LoggerFactory.getLogger(NaCosConsumerFeignController.class);

    @Resource
    private EchoService echoService;

    @GetMapping(value = "/echo/hi")
    public String echo() {

        logger.info("[NaCosConsumerFeignController].[echo]------->>> 执行请求");

        return echoService.echo("Feign");
    }
}
