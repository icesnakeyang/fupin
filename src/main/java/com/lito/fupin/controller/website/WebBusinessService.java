package com.lito.fupin.controller.website;

import com.lito.fupin.business.category.ICategoryBusinessService;
import com.lito.fupin.business.paper.IPaperBusinessService;
import com.lito.fupin.meta.category.entity.Category;
import com.lito.fupin.meta.category.service.ICategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class WebBusinessService implements IWebBusinessService {
    private final ICategoryBusinessService iCategoryBusinessService;
    private final IPaperBusinessService iPaperBusinessService;

    Logger logger = LoggerFactory.getLogger(getClass());

    public WebBusinessService(ICategoryBusinessService iCategoryBusinessService,
                              IPaperBusinessService iPaperBusinessService) {
        this.iCategoryBusinessService = iCategoryBusinessService;
        this.iPaperBusinessService = iPaperBusinessService;
    }

    /**
     * 组装新闻中心首页
     * 读取新闻中心分类下的二级分类
     * 读取用户选择的二级分类下的文章列表
     * 如果没有选择二级分类，默认为第一个二级分类下的文章列表
     *
     * @param in
     * @return
     * @throws Exception
     */
    @Override
    public Map loadNewsHomePage(Map in) throws Exception {
        /**
         * 1、读取category得到pid
         * 2、根据pid查询所有sub category
         * 3、循环category list，把当前category设置为active
         * 4、查询当前category的paper list，分页
         *
         */
        String categoryId = (String) in.get("categoryId");
        Integer pageIndex = (Integer) in.get("pageIndex");
        Integer pageSize = (Integer) in.get("pageSize");

        /**
         * 如果没有categoryId，就是新闻首页，用户没有点击具体的二级子栏目
         * 此时需读取所有二级子栏目
         * 默认选择第1个子栏目，读取该分类下的文章列表
         */

        Map qIn = new HashMap();
        ArrayList<Category> currCategoryList = new ArrayList<>();
        if (categoryId == null) {
            qIn.put("categoryName", "新闻中心");
            Category rootCategory = (Category) iCategoryBusinessService.getCategory(qIn).get("category");
            qIn = new HashMap();
            qIn.put("pid", rootCategory.getCategoryId());
            currCategoryList = (ArrayList<Category>) iCategoryBusinessService.listSubCategory(qIn).get("categoryList");
            currCategoryList.get(0).setActive(true);
            categoryId = currCategoryList.get(0).getCategoryId();
        } else {
            //当前选择的category
            qIn.put("categoryId", categoryId);
            Category currCategory = (Category) iCategoryBusinessService.getCategory(qIn).get("category");
            //当前同级别的category
            qIn = new HashMap();
            qIn.put("pid", currCategory.getPid());
            currCategoryList = (ArrayList<Category>) iCategoryBusinessService.listSubCategory(qIn).get("categoryList");
            for (int i = 0; i < currCategoryList.size(); i++) {
                if (currCategoryList.get(i).getCategoryId().equals(categoryId)) {
                    currCategoryList.get(i).setActive(true);
                }
            }
        }

        Map out = new HashMap();
        out.put("categoryList", currCategoryList);

        qIn = new HashMap();
        qIn.put("categoryId", categoryId);
        qIn.put("pageIndex", pageIndex);
        qIn.put("pageSize", pageSize);
        out.put("paperList", iPaperBusinessService.listPaperList(qIn).get("list"));
        return out;
    }

    @Override
    public Map loadPaperDetailPage(Map in) throws Exception {
        Map out = iPaperBusinessService.getPaperByPaerId(in);
        return out;
    }
}
