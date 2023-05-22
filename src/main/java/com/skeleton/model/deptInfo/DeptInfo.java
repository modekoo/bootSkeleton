package com.skeleton.model.deptInfo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.skeleton.model.userInfo.UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="dept_info")
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "deptNo")
@SequenceGenerator(name = "dept_info_seq_generation", sequenceName = "dept_info_seq",
                    initialValue = 10000, allocationSize = 50)
public class DeptInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_info_seq_generation")
    private int deptNo;

    private String deptName;

    @OneToMany(mappedBy = "deptInfo")
    List<UserInfo> userList;
}
