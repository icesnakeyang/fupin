package com.lito.fupin.controller.adminPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminPageController {
    @RequestMapping("/loginPage")
    public String loginPage(){
        return "/admin/user/login";
    }
}
