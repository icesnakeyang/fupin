package com.lito.fupin.controller.adminPage;

import com.lito.fupin.business.user.IUserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminPageController {

    /**
     * 用户登录页面
     * @return
     */
    @RequestMapping("/loginPage")
    public String loginPage(){
        return "admin/user/login";
    }

    /**
     * 管理员首页面板
     * @return
     */
    @RequestMapping("/dashboard")
    public String dashboard(){
        return "admin/dashboard";
    }

    /**
     * 组织管理页面
     * @return
     */
    @RequestMapping("/organize")
    public String organizePage(){
        return "admin/organize/organize";
    }

    /**
     * 栏目管理页面
     * @return
     */
    @RequestMapping("/category")
    public String categoryPage(){
        return "admin/category/category";
    }

    /**
     * 文章管理页面
     * @return
     */
    @RequestMapping("/paper")
    public String paperPage(){
        return "admin/paper/paper";
    }

    /**
     * 用户管理页面
     * @return
     */
    @RequestMapping("/user")
    public String userPage(){
        return "admin/user/user";
    }
}
