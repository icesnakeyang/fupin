package com.lito.fupin.meta.user.dao;

import com.lito.fupin.meta.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserDao {
    /**
     * 新增一个用户
     * @param user
     */
    void createUser(User user);

    /**
     * 根据登录名和密码查询用户
     * @param qIn
     * @return
     */
    User getUserByLoginNamePassword(Map qIn);

    User getUserByToken(String token);
}
