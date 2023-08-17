package com.skeleton.controller;

import com.skeleton.dto.userInfo.LoginUserInfoDTO;
import com.skeleton.entity.userInfo.UserInfo;
import com.skeleton.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginUserInfoDTO loginUserInfoDTO) {
        return ResponseEntity.ok(userService.selectByUserIdAndPassword(loginUserInfoDTO));
    }

    @PostMapping("/jpa/login")
    public ResponseEntity jpaLogin(@RequestBody @Valid LoginUserInfoDTO loginUserInfoDTO) {
        return ResponseEntity.ok(userService.selectByUserIdAndPasswordJPA(loginUserInfoDTO));
    }

    @PostMapping("/jpa/save")
    public ResponseEntity jpaSaveUser(@RequestBody UserInfo userInfo) {
        return ResponseEntity.ok(userService.saveUserJPA(userInfo));
    }

}
