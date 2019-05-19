package com.lito.fupin.controller.pages;

import com.lito.fupin.business.organize.IOrganizeBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/pages")
public class PagesController {
    private final IOrganizeBusinessService iOrganizeBusinessService;

    @Autowired
    public PagesController(IOrganizeBusinessService iOrganizeBusinessService) {
        this.iOrganizeBusinessService = iOrganizeBusinessService;
    }

    @RequestMapping("/organizeList")
    public String getUser(Model model) {
        try {
            Map out = iOrganizeBusinessService.listOrganize();
            model.addAttribute("organizeList", out.get("organizeList"));

        } catch (Exception ex) {

        }
        return "index";
    }

    @RequestMapping("/test")
    public String testPage(Model model) {
        return "/admin/user/testLogin";
    }
}