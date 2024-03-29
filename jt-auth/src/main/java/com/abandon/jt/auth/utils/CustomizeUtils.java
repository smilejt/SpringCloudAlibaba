package com.abandon.jt.auth.utils;

import com.abandon.jt.auth.entity.UserInfo;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

/**
 * @createData: 2019-06-24 17:13
 * @author: LongJunTao
 * @Description:
 */
public class CustomizeUtils {
    /**
     * 加盐加密的策略非常多,根据实际业务来
     */
    public static void encryptPassword(UserInfo user) {
        String salt = UUID.randomUUID().toString();
        String temPassword = salt + user.getPlainPassword();
        String md5Password = DigestUtils.md5DigestAsHex(temPassword.getBytes());
        user.setSalt(salt);
        user.setPassword(md5Password);
    }

    public static boolean decryptPassword(UserInfo user, String plainPassword) {
        String temPassword = user.getSalt() + plainPassword;
        String md5Password = DigestUtils.md5DigestAsHex(temPassword.getBytes());
        return user.getPassword().equals(md5Password);
    }

    public static String getCurrentDateTime() {
        TimeZone zone = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(zone);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
}
