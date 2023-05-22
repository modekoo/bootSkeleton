package com.skeleton.repository;

import com.skeleton.model.userInfo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    List<UserInfo> findAll();

    UserInfo findUserInfoByUserNo(Long userNo);

    UserInfo findUserInfoByUserIdAndPassword(String userId, String password);
}
