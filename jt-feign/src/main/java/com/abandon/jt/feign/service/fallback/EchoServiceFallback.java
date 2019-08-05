package com.abandon.jt.feign.service.fallback;

import com.abandon.jt.feign.service.EchoService;
import org.springframework.stereotype.Component;

/**
 * @createData: 2019-08-05 14:21
 * @author: LongJunTao
 * @Description:
 */
@Component
public class EchoServiceFallback implements EchoService {
    @Override
    public String echo(String message) {
        return "请求失败!";
    }
}
