package com.skeleton.service;

import com.skeleton.dao.UserDao;
import com.skeleton.dto.CommonResponse;
import com.skeleton.dto.user.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Transactional(readOnly = true)
    public CommonResponse selectByUserIdAndPassword(UserDto userDto){
        CommonResponse res = new CommonResponse();

        Map<String, Object> resultMap = userDao.selectByUserIdAndPassword(userDto);
        if(resultMap != null)
            res.setResultData(resultMap);
//        else
//            throw new APIException("추후~");

        return res;
    }

}
