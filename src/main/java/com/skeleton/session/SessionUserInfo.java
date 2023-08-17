package com.skeleton.session;

import com.skeleton.entity.userInfo.UserInfo;
import lombok.Data;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
@Component
public class SessionUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private int userNo;
    private String userId;
    private String userName;

    private int deptNo;
    private int companyNo;

    public void setUserInfo(UserInfo userInfo){
        this.userNo = userInfo.getUserNo();
        this.userId = userInfo.getUserId();
        this.userName = userInfo.getUserName();

        this.deptNo = userInfo.getDeptInfo() != null ? userInfo.getDeptInfo().getDeptNo() : 0;
        this.companyNo = userInfo.getCompanyInfo() != null ? userInfo.getCompanyInfo().getCompanyNo() : 0;
    }


}
