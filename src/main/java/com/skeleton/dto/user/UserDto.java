package com.skeleton.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {
    @NotBlank
    String userId;
    @NotBlank
    String password;
}
