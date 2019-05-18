package com.lito.fupin;

import com.lito.fupin.common.GGF;
import com.lito.fupin.meta.user.entity.User;
import com.lito.fupin.meta.user.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FupinApplicationTests {
    @Autowired
    private IUserService iUserService;

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
            out.put("user", user);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
