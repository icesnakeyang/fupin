package com.lito.fupin.meta.user.service;

import com.lito.fupin.meta.user.entity.User;

import java.util.ArrayList;

public interface IUserService {
    /**
     * 新增一个用户
     * @param user
     * @throws Exception
     */
    void createUser(User user) throws Exception;

    /**
     * 根据登录名和密码查询用户
     * @param loginName
     * @param password
     * @return
     * @throws Exception
     */
    User getUserByLoginNamePassword(String loginName, String password) throws Exception;

    User getUserByUserId(String userId) throws Exception;

    User getUserByToken(String token) throws Exception;

    ArrayList<User> listUserByPid(String organizeId) throws Exception;

    /**
     * 读取一个机构下的所有用户
     * @param organizeId
     * @return
     * @throws Exception
     */
    ArrayList listUserByOrganizeId(String organizeId) throws Exception;
}
