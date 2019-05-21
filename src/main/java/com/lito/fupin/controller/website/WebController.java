package com.lito.fupin.controller.website;

import com.lito.fupin.business.paper.IPaperBusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/page")
public class WebController {
    private final IPaperBusinessService iPaperBusinessService;

    Logger logger= LoggerFactory.getLogger(getClass());

    public WebController(IPaperBusinessService iPaperBusinessService) {
        this.iPaperBusinessService = iPaperBusinessService;
    }

    @RequestMapping("/listPaperToShow")
    public String listPaperToShow(Model model) {

        String name = "lkdjslfsd";
        model.addAttribute("name", name);
        return "web/paper";

    }
}
