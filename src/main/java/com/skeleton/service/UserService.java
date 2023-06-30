package com.skeleton.service;

import com.skeleton.dao.UserDao;
import com.skeleton.dto.CommonResponse;
import com.skeleton.dto.userInfo.LoginUserInfoDTO;
import com.skeleton.model.userInfo.UserInfo;
import com.skeleton.repository.UserInfoRepository;
import com.skeleton.session.SessionUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    UserInfoRepository userInfoRepository;
    @Resource
    SessionUserInfo sessionUserInfo;

    public CommonResponse selectByUserIdAndPassword(LoginUserInfoDTO loginUserInfoDTO){
        CommonResponse res = new CommonResponse();
        log.debug("userInfo = {}", sessionUserInfo);
        sessionUserInfo = userDao.selectByUserIdAndPassword(loginUserInfoDTO);
        if(sessionUserInfo != null)
            res.setResultData(sessionUserInfo);

        return res;
    }

    public CommonResponse selectByUserIdAndPasswordJPA(LoginUserInfoDTO loginUserInfoDTO){
        CommonResponse res = new CommonResponse();
        log.debug("userInfo = {}", sessionUserInfo);
        UserInfo userInfo = userInfoRepository.findUserInfoByUserIdAndPassword(loginUserInfoDTO.getUserId(), loginUserInfoDTO.getPassword());
        if(userInfo != null) {
            sessionUserInfo.setUserInfo(userInfo);
            res.setResultData(userInfo);
        }

        return res;
    }

    public CommonResponse saveUserJPA(UserInfo userInfo){
        CommonResponse res = new CommonResponse();
        log.debug("userInfo = {}", userInfo);
        userInfo = userInfoRepository.saveAndFlush(userInfo);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userNo", userInfo.getUserNo());
        res.setResultData(resultMap);
        return res;
    }
}
