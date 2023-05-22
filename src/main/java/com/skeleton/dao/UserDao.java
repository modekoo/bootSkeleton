package com.skeleton.dao;

import com.skeleton.dto.userInfo.LoginUserInfoDTO;
import com.skeleton.session.SessionUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    SessionUserInfo selectByUserIdAndPassword(LoginUserInfoDTO loginUserInfoDTO);

}
