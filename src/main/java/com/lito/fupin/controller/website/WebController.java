package com.lito.fupin.controller.website;

import com.lito.fupin.business.category.ICategoryBusinessService;
import com.lito.fupin.business.paper.IPaperBusinessService;
import com.lito.fupin.meta.category.entity.Category;
import com.lito.fupin.meta.paper.entity.Paper;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.HsqlMaxValueIncrementer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WebController {
    private final IPaperBusinessService iPaperBusinessService;
    private final ICategoryBusinessService iCategoryBusinessService;

    Logger logger = LoggerFactory.getLogger(getClass());

    public WebController(IPaperBusinessService iPaperBusinessService,
                         ICategoryBusinessService iCategoryBusinessService) {
        this.iPaperBusinessService = iPaperBusinessService;
        this.iCategoryBusinessService = iCategoryBusinessService;
    }

    @GetMapping("/")
    public String rootMe() {
        return "redirect:/homePage";
    }

    @GetMapping("/homePage")
    public String homePage(Model model) {
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

    @GetMapping("/rootNewsList")
    public String newsListPage(Model model) {
        try {
            Map in = new HashMap();
            in.put("pageIndex", 0);
            in.put("pageSize", 10);
            in.put("categoryName", "新闻中心");
            Map out = new HashMap();
            out.put("categoryList", iCategoryBusinessService.listSubCategory(in).get("categoryList"));
            in.put("categoryId", ((ArrayList<Category>) out.get("categoryList")).get(0).getCategoryId());
            out.put("list", iPaperBusinessService.listPaperList(in).get("list"));
            model.addAttribute("out", out);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return "web/newsList";
    }

    @GetMapping("/newsList/{categoryId}")
    public String newsList(Model model, @PathVariable String categoryId){
        try {
            Map in=new HashMap();
            in.put("categoryId", categoryId);
            Map out=new HashMap();
            out.put("categoryList", iCategoryBusinessService.listSubCategory(in).get("categoryList"));
            in.put("categoryId", categoryId)
            in.put("categoryId", categoryId);
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return "web/newsList";
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
