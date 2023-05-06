package com.skeleton.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CommonResponse {

    String resultMsg = "";
    String resultCode = "200";
    Map<String, Object> resultData;

    public CommonResponse(){
        resultData = new HashMap<>();
    }
}
