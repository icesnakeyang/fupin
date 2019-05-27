package com.lito.fupin.meta.user.dao;

import com.lito.fupin.meta.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
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

    ArrayList<User> listUserByPid(String organizeId);

    /**
     * 查询一个机构下的所有用户
     * @param organizeId
     * @return
     */
    ArrayList<User> listUserByOrganize(String organizeId);

    /**
     * 根据loginName查询user用户
     * @param loginName
     * @return
     */
    User getUserByLoginName(String loginName);
}
