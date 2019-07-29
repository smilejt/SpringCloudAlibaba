package com.abandon.jt.auth.service;

import com.abandon.jt.common.utils.CustomizeResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @createData: 2019-07-04 13:51
 * @author: LongJunTao
 * @Description:
 */
public interface AuthService {

    /**
     * 用户登陆
     * @param account account
     * @param password password
     * @param request request
     * @param response response
     * @return CustomizeResult
     */
    CustomizeResult userLogin(String account, String password, HttpServletRequest request, HttpServletResponse response);

    /**
     * 用户注销
     * @param token token
     */
    void logout(String token);

    /**
     * 验证Token有效性
     * @param token token
     * @return CustomizeResult
     */
    CustomizeResult queryUserByToken(String token);
}
