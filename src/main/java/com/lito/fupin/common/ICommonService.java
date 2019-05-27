package com.lito.fupin.common;

import com.lito.fupin.meta.user.entity.User;

import java.util.Map;

public interface ICommonService {
    User checkUser(String token, String role) throws Exception;
}
