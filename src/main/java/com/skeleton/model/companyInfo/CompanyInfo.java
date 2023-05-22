package com.skeleton.model.companyInfo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.skeleton.model.userInfo.UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="company_info")
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "companyNo")
@SequenceGenerator(name = "company_info_seq_generation", sequenceName = "company_info_seq",
                    initialValue = 10000, allocationSize = 50)
public class CompanyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_info_seq_generation")
    private int companyNo;
    
    private String companyName;

    @OneToMany(mappedBy = "companyInfo")
    List<UserInfo> userList;
}
