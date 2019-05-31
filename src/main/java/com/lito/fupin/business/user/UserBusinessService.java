package com.lito.fupin.business.user;

import com.lito.fupin.common.GGF;
import com.lito.fupin.meta.organize.entity.Organize;
import com.lito.fupin.meta.organize.service.IOrganizeService;
import com.lito.fupin.meta.user.entity.User;
import com.lito.fupin.meta.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserBusinessService implements IUserBusinessService {
    private final IUserService iUserService;
    private final IOrganizeService iOrganizeService;

    @Autowired
    public UserBusinessService(IUserService iUserService,
                               IOrganizeService iOrganizeService) {
        this.iUserService = iUserService;
        this.iOrganizeService = iOrganizeService;
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
        String token = in.get("token").toString();
        String loginName = in.get("loginName").toString();
        String password = in.get("password").toString();
        String organizeId = in.get("organizeId").toString();
        String permission = in.get("permission").toString();

        User loginUser = iUserService.getUserByToken(token);
        if (loginUser == null) {
            throw new Exception("10003");
        }
        if (!loginUser.getPermission().equals("超级管理员") &&
                !loginUser.getPermission().equals("普通管理员")) {
            throw new Exception("10005");
        }

        User checkUser = iUserService.getUserByLoginName(loginName);
        if (checkUser != null) {
            throw new Exception("10006");
        }

        User user = new User();
        user.setCreatedTime(new Date());
        user.setPassword(GGF.encoderByMd5(password));
        user.setLoginName(loginName);
        user.setOrganizeId(organizeId);
        user.setUserId(GGF.UUID().toString());
        user.setToken(GGF.UUID().toString());
        user.setTokenTime(new Date());
        user.setPermission(permission);
        iUserService.createUser(user);

        Map out = new HashMap();
        out.put("admin/user", user);
        return out;
    }

    /**
     * 用户登录
     *
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

    /**
     * 查询用户权限下的所有同级和下级用户
     *
     * @param in
     * @return
     * @throws Exception
     */
    @Override
    public Map listUserByToken(Map in) throws Exception {
        String token = in.get("token").toString();
        /**
         * 通过token读取用户的organizeId
         * 通过organizeId读取所有下级机构
         * 读取每个下级机构的用户
         */
        User user = iUserService.getUserByToken(token);
        Organize organize = iOrganizeService.getOrganizeById(user.getOrganizeId());

        ArrayList<User> userList = listSubUser(organize);

        Map out = new HashMap();
        out.put("userList", userList);
        return out;
    }

    private ArrayList listSubUser(Organize organize) throws Exception {
        //查询当前机构的所有用户
//        ArrayList<User> userList = iUserService.listUserByOrganizeId(organize.getOrganizeId());
        //检查当前机构是否有下一级机构
        ArrayList<Organize> organizeList = iOrganizeService.listOrganizeByPid(organize.getOrganizeId());
        ArrayList<User> userList=new ArrayList<>();
        if (organizeList.size() > 0) {
            for (int i = 0; i < organizeList.size(); i++) {
                userList.addAll(listSubUser(organizeList.get(i)));
            }
        }
        return userList;
    }
}
