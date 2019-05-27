package com.lito.fupin.controller.adminPage;

import com.lito.fupin.business.user.IUserBusinessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminPageController {
    private final IUserBusinessService iUserBusinessService;

    public AdminPageController(IUserBusinessService iUserBusinessService) {
        this.iUserBusinessService = iUserBusinessService;
    }

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
     * 文章审核页面
     * @return
     */
    @RequestMapping("/approvePaper")
    public String approvePaperPage(){
        return "admin/paper/approvePaper";
    }

    /**
     * 用户管理页面
     * @return
     */
    @RequestMapping("/user")
    public String userPage(Model model, HttpServletRequest httpServletRequest){

        try {
            String token = httpServletRequest.getHeader("token");
            Map in = new HashMap();
            in.put("token", token);
            Map out = iUserBusinessService.listUserByToken(in);
            model.addAttribute("map",out);
        }catch (Exception ex){

        }
        return "admin/user/user";
    }

    @RequestMapping("/editPaper")
    public String editPaperPage(){
        return "admin/paper/editPaper";
    }

    @RequestMapping("/pendingPaper")
    public String myPendingPaper(){
        return "admin/paper/pendingPaper";
    }

}
