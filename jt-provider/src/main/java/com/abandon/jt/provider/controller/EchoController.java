package com.abandon.jt.provider.controller;

import com.abandon.jt.provider.ProviderApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @createData: 2019-08-05 14:35
 * @author: LongJunTao
 * @Description:
 */
@RestController
public class EchoController {

    private static final Logger logger = LoggerFactory.getLogger(ProviderApplication.class);

    @GetMapping(value = "/echo/{message}")
    public String echo(@PathVariable String message) {

        logger.info("[EchoController].[echo]------->>> 执行成功");

        return "Hello NaCos Discovery " + message;
    }
}
