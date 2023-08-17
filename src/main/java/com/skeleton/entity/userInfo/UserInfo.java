package com.skeleton.entity.userInfo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.skeleton.entity.companyInfo.CompanyInfo;
import com.skeleton.entity.deptInfo.DeptInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="user_info")
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userNo")
@SequenceGenerator(name = "user_info_seq_generation", sequenceName = "user_info_seq",
        initialValue = 10000, allocationSize = 50)
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_info_seq_generation")
    private int userNo;

    private String userId;
    private String userName;
    private String password;

    @ManyToOne @JoinColumn(name="dept_no")
    DeptInfo deptInfo;

    @ManyToOne @JoinColumn(name="company_no")
    CompanyInfo companyInfo;
}
