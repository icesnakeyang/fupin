package com.lito.fupin.meta.user.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户表
 */
@Data
public class User {
    private Integer ids;
    private String UserId;
    private String loginName;
    private String password;
    private Date createdTime;
    private String organizeId;
    private String organizeName;
    private String token;
    private Date tokenTime;
    private String permission;
}
