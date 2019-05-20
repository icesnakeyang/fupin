package com.lito.fupin.business.user;

import com.lito.fupin.common.GGF;
import com.lito.fupin.meta.user.entity.User;
import com.lito.fupin.meta.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserBusinessService implements IUserBusinessService {
    private final IUserService iUserService;

    @Autowired
    public UserBusinessService(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    /**
     * 注册一个新用户
     *
     * @param in
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map register(Map in) throws Exception {
        String loginName = in.get("loginName").toString();
        String password = in.get("password").toString();
        String organizeId = in.get("organizeId").toString();

        User user = new User();
        user.setCreatedTime(new Date());
        user.setPassword(GGF.encoderByMd5(password));
        user.setLoginName(loginName);
        user.setOrganizeId(organizeId);
        user.setUserId(GGF.UUID().toString());
        user.setToken(GGF.UUID().toString());
        user.setTokenTime(new Date());
        iUserService.createUser(user);

        Map out = new HashMap();
        out.put("admin/user", user);
        return out;
    }

    /**
     * 用户登录
     * @param in
     * @return
     * @throws Exception
     */
    @Override
    public Map login(Map in) throws Exception {
        String loginName = in.get("loginName").toString();
        String password = in.get("password").toString();
        password = GGF.encoderByMd5(password);
        User user = iUserService.getUserByLoginNamePassword(loginName, password);
        Map out = new HashMap();
        out.put("user", user);
        return out;
    }
}
