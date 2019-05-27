package com.lito.fupin.controller.api.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Integer ids;
    private String UserId;
    private String loginName;
    private String password;
    private String organizeId;
    private String permission;
}
