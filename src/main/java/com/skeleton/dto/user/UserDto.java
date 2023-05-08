package com.skeleton.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {
    @NotBlank(message = "userId not blank")
    String userId;
    @NotBlank(message = "password not blank")
    String password;
}
