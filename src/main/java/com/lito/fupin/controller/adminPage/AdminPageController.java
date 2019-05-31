package com.lito.fupin.controller.adminPage;

import com.lito.fupin.business.user.IUserBusinessService;
import org.hibernate.validator.constraints.pl.REGON;
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
     *
     * @return
     */
    @RequestMapping("/loginPage")
    public String loginPage() {
        return "admin/user/login";
    }

    /**
     * 管理员首页面板
     *
     * @return
     */
    @RequestMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }

    /**
     * 组织管理页面
     *
     * @return
     */
    @RequestMapping("/organize")
    public String organizePage(Model model) {
        model.addAttribute("organize", true);
        return "admin/organize/organize";
    }

    /**
     * 栏目管理页面
     *
     * @return
     */
    @RequestMapping("/category")
    public String categoryPage(Model model) {
        model.addAttribute("category", true);
        return "admin/category/category";
    }

    /**
     * 文章审核页面
     *
     * @return
     */
    @RequestMapping("/approvePaper")
    public String approvePaperPage() {
        return "admin/paper/approvePaper";
    }

    /**
     * 用户管理页面
     *
     * @return
     */
    @RequestMapping("/user")
    public String userPage(Model model, HttpServletRequest httpServletRequest) {
        try {
            String token = httpServletRequest.getHeader("token");
            Map in = new HashMap();
            in.put("token", token);
//            Map out = iUserBusinessService.listUserByToken(in);
//            model.addAttribute("map",out);
            model.addAttribute("user", true);
        } catch (Exception ex) {

        }
        return "admin/user/user";
    }

    /**
     * 创建新的文章页面
     *
     * @return
     */
    @RequestMapping("/createPaper")
    public String createPaper(Model model) {
        model.addAttribute("paper", true);
        model.addAttribute("createPaper", true);
        return "admin/paper/paperNew";
    }

    @RequestMapping("/auditPaperList")
    public String auditPaperListPage(Model model) {
        model.addAttribute("paper", true);
        model.addAttribute("auditPaperList", true);
        return "admin/paper/paperAuditList";
    }

    @RequestMapping("/auditPaperPage")
    public String auditPaperPage(Model model) {
        return "admin/paper/paperAuditPage";
    }

    /**
     * 我创建的等待审核的文章
     *
     * @return
     */
    @RequestMapping("/pendingPaper")
    public String myPendingPaper(Model model) {
        model.addAttribute("paper", true);
        model.addAttribute("pendingPaper", true);
        return "admin/paper/paperPendingList";
    }

}
