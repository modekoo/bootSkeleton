package com.skeleton.dao;

import com.skeleton.dto.user.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface UserDao {

    Map<String, Object> selectByUserIdAndPassword(UserDto userDto);

}
