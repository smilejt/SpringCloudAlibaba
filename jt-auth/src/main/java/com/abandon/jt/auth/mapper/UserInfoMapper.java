package com.abandon.jt.auth.mapper;

import com.abandon.jt.auth.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserInfoMapper {

    UserInfo findByAccount(String account);
}
