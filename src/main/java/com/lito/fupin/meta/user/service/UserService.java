package com.lito.fupin.meta.user.service;

import com.lito.fupin.meta.user.dao.UserDao;
import com.lito.fupin.meta.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements IUserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 新增一个用户
     *
     * @param user
     * @throws Exception
     */
    @Override
    public void createUser(User user) throws Exception {
        userDao.createUser(user);
    }

    /**
     * 根据登录名和密码查询用户
     *
     * @param loginName
     * @param password
     * @return
     * @throws Exception
     */
    @Override
    public User getUserByLoginNamePassword(String loginName, String password) throws Exception {
        Map qIn = new HashMap();
        qIn.put("loginName", loginName);
        qIn.put("password", password);
        User user = userDao.getUserByLoginNamePassword(qIn);
        return user;
    }

    /**
     * 根据loginName查询user用户
     * @param loginName
     * @return
     * @throws Exception
     */
    @Override
    public User getUserByLoginName(String loginName) throws Exception {
        User user=userDao.getUserByLoginName(loginName);
        return user;
    }

    @Override
    public User getUserByUserId(String userId) throws Exception {
        return null;
    }

    @Override
    public User getUserByToken(String token) throws Exception {
        User user = userDao.getUserByToken(token);
        return user;
    }

    @Override
    public ArrayList<User> listUserByPid(String organizeId) throws Exception {
        ArrayList<User> userList = userDao.listUserByPid(organizeId);
        return userList;
    }

    /**
     * 查询一个机构下的所有用户
     * @param organizeId
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList listUserByOrganizeId(String organizeId) throws Exception {
        ArrayList<User> userArrayList=userDao.listUserByOrganize(organizeId);
        return userArrayList;
    }
}
