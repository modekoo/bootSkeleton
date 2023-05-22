package com.skeleton.dto.userInfo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginUserInfoDTO {
    @NotBlank(message = "userId not blank")
    String userId;
    @NotBlank(message = "password not blank")
    String password;
}
