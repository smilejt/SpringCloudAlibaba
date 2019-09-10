package com.abandon.jt.auth.service.impl;

import com.abandon.jt.auth.entity.UserInfo;
import com.abandon.jt.auth.mapper.UserInfoMapper;
import com.abandon.jt.auth.repository.JedisClient;
import com.abandon.jt.auth.service.AuthService;
import com.abandon.jt.auth.utils.CustomizeUtils;
import com.abandon.jt.common.utils.CookieUtils;
import com.abandon.jt.common.utils.CustomizeResult;
import com.abandon.jt.common.utils.JsonUtils;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.client.naming.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @createData: 2019-07-04 14:00
 * @author: LongJunTao
 * @Description:
 */
@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private JedisClient jedisClient;

    @NacosValue(value = "${REDIS_USER_SESSION_KEY:REDIS_USER_SESSION}", autoRefreshed = true)
    private String redisUserSession;

    @NacosValue(value = "${SSO_SESSION_EXPIRE:3600}", autoRefreshed = true)
    private Integer ssoSessionExpire;

    @Override
    public CustomizeResult userLogin(String account, String password,
                                     HttpServletRequest request, HttpServletResponse response) {
        logger.info("[UserService].[userLogin]验证账号密码开始");
        // 判断账号密码是否正确
        UserInfo user = userInfoMapper.findByAccount(account);
        if (null == user || !CustomizeUtils.decryptPassword(user, password)) {
            logger.info("[UserService].[userLogin]账号或密码错误");
            return CustomizeResult.build(400, "账号名或密码错误");
        }
        // 生成token
        String token = UUID.randomUUID().toString();
        // 清空密码和盐避免泄漏
        String userPassword = user.getPassword();
        String userSalt = user.getSalt();
        user.setPassword(null);
        user.setSalt(null);
        // 把用户信息写入 redis
        jedisClient.set(redisUserSession + ":" + token, JsonUtils.objectToJson(user));
        // user 已经是持久化对象，被保存在session缓存当中，若user又重新修改属性值，那么在提交事务时，此时 hibernate对象就会拿当前这个user对象和保存在session缓存中的user对象进行比较，如果两个对象相同，则不会发送update语句，否则会发出update语句。
        user.setPassword(userPassword);
        user.setSalt(userSalt);
        // 设置 session 的过期时间
        jedisClient.expire(redisUserSession + ":" + token, ssoSessionExpire);
        // 添加写 cookie 的逻辑，cookie 的有效期是关闭浏览器就失效。
        CookieUtils.setCookie(request, response, "USER_TOKEN", token);

        logger.info("[UserService].[userLogin]登录成功account:{}", user.getAccount());
        // 返回token
        return CustomizeResult.ok(token);
    }

    @Override
    public void logout(String token) {
        jedisClient.del(redisUserSession + ":" + token);
    }

    @Override
    public CustomizeResult queryUserByToken(String token) {
        // 根据token从redis中查询用户信息
        String json = jedisClient.get(redisUserSession + ":" + token);
        // 判断是否为空
        if (StringUtils.isEmpty(json)) {
            logger.info("[UserService].[queryUserByToken]session已过期{}", token);
            return CustomizeResult.build(400, "此session已经过期，请重新登录");
        }
        // 更新过期时间
        jedisClient.expire(redisUserSession + ":" + token, ssoSessionExpire);
        // 返回用户信息
        return CustomizeResult.ok(JsonUtils.jsonToPojo(json, UserInfo.class));
    }
}
