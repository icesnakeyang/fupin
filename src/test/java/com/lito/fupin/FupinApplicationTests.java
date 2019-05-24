package com.lito.fupin;

import com.lito.fupin.business.user.IUserBusinessService;
import com.lito.fupin.common.GGF;
import com.lito.fupin.meta.organize.entity.Organize;
import com.lito.fupin.meta.organize.service.IOrganizeService;
import com.lito.fupin.meta.user.entity.User;
import com.lito.fupin.meta.user.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FupinApplicationTests {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IUserBusinessService iUserBusinessService;
    @Autowired
    private IOrganizeService iOrganizeService;

    @Test
    public void contextLoads() {
        try {
            User user = new User();
            user.setLoginName("superadmin");
            user.setPassword(GGF.encoderByMd5("123456"));
            user.setUserId(GGF.UUID().toString());
            user.setCreatedTime(new Date());
            iUserService.createUser(user);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void login() {
        try {
            String loginName = "superadmin";
            String password = "123456";
            password = GGF.encoderByMd5(password);
            User user = iUserService.getUserByLoginNamePassword(loginName, password);
            Map out = new HashMap();
            out.put("admin/user", user);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void listUser() {
        try {
            Organize organize = iOrganizeService.getOrganizeById("c47809ee-f158-4f9b-92fa-28abec108f0e");
//            ArrayList<User> userList = iUserBusinessService.listSubUser(organize);
            int i = 0;
            i = 5;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
