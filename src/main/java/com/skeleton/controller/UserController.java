package com.skeleton.controller;

import com.skeleton.dto.CommonResponse;
import com.skeleton.dto.user.UserDto;
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
    public ResponseEntity login(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userService.selectByUserIdAndPassword(userDto));
    }
}
