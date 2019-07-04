package com.abandon.jt.auth.service;

import com.abandon.jt.common.utils.CustomizeResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {

    /**
     * 用户登陆
     * @param account
     * @param password
     * @param request
     * @param response
     * @return
     */
    CustomizeResult userLogin(String account, String password, HttpServletRequest request, HttpServletResponse response);

    /**
     * 用户注销
     * @param token
     */
    void logout(String token);

    /**
     * 验证Token有效性
     * @param token
     * @return
     */
    CustomizeResult queryUserByToken(String token);
}
