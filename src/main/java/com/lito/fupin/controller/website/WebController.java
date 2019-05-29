package com.lito.fupin.controller.website;

import com.lito.fupin.business.paper.IPaperBusinessService;
import com.lito.fupin.meta.paper.entity.Paper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/page")
public class WebController {
    private final IPaperBusinessService iPaperBusinessService;

    Logger logger = LoggerFactory.getLogger(getClass());

    public WebController(IPaperBusinessService iPaperBusinessService) {
        this.iPaperBusinessService = iPaperBusinessService;
    }

    @GetMapping("/listPaperToShow")
    public String listPaperToShow(Model model) {
        try {
            Map in = new HashMap();
            in.put("categoryId", "5357e621-6952-4287-bbf3-1e878eaeff89");
//            in.put("organizeId", "b4daa685-2a6b-4475-b194-2839541d6c59");
            in.put("pageIndex", 0);
            in.put("pageSize", 10);
            Map out = iPaperBusinessService.listPaperToShow(in);
            model.addAttribute("data", out);
        } catch (Exception ex) {

        }
        return "web/index";
    }

    @GetMapping("/paperDetail/{paperId}")
    public String paperDetailPage(Model model, @PathVariable String paperId) {
        try {
            Map in = new HashMap();
            in.put("paperId", paperId);
            Map out = iPaperBusinessService.getPaperByPaerid(in);
            model.addAttribute("paper", out.get("paper"));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return "web/paperDetail";
    }
}
