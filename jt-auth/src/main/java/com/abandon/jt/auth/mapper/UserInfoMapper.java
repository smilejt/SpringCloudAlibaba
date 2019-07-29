package com.abandon.jt.auth.mapper;

import com.abandon.jt.auth.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @createData: 2019-07-04 13:51
 * @author: LongJunTao
 * @Description:
 */
@Mapper
@Repository
public interface UserInfoMapper {

    /**
     * 根据Account获取用户信息
     * @param account
     * @return
     */
    UserInfo findByAccount(String account);
}
