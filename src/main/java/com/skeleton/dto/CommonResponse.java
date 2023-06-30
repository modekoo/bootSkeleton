package com.skeleton.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class CommonResponse<T> {

    String resultMsg = "";
    String resultCode = "200";
    T resultData;

    public CommonResponse(){
        resultData = (T) new HashMap<>();
    }

    public CommonResponse(String resultCode, String resultMsg){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        resultData = (T) new HashMap<>();
    }

}
