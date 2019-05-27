package com.lito.fupin.common;

import com.lito.fupin.meta.user.entity.User;
import com.lito.fupin.meta.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService implements ICommonService {
    private final IUserService iUserService;

    public CommonService(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @Override
    public User checkUser(String token, String role) throws Exception {
        User loginUser = iUserService.getUserByToken(token);
        if (loginUser == null) {
            throw new Exception("10003");
        }
        if (role.equals("admin")) {
            if (!loginUser.getPermission().equals("超级管理员") &&
                    !loginUser.getPermission().equals("普通管理员")) {
                throw new Exception("10005");
            }
        }
        if (role.equals("stuff")) {
            if (!loginUser.getPermission().equals("超级管理员") &&
                    !loginUser.getPermission().equals("网站管理员") &&
                    !loginUser.getPermission().equals("普通管理员")) {
                throw new Exception("10005");
            }
        }
        return loginUser;
    }
}
