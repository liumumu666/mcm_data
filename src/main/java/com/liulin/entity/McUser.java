package com.liulin.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class McUser implements Serializable {

    private Integer mcUserId;
    private String mcUserNiname;
    private String mcUserSex;
    private String mcUserName;
    private String mcUserPassword;
    private Integer mcUserJob;
    private String  mcUserJobName;
    private Integer mcUserAge;
    private String mcUserEmail;

}
