package com.lito.fupin.business.category;

import com.lito.fupin.common.GGF;
import com.lito.fupin.meta.category.entity.Category;
import com.lito.fupin.meta.category.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.HashAttributeSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryBusinessService implements ICategoryBusinessService {
    private final ICategoryService iCategoryService;

    public CategoryBusinessService(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createCategory(Map in) throws Exception {
        String categoryName = in.get("categoryName").toString();
        String pid = (String) in.get("pid");

        Category category = new Category();
        category.setCategoryId(GGF.UUID().toString());
        category.setCategoryName(categoryName);
        category.setPid(pid);
        iCategoryService.createCategory(category);
    }

    @Override
    public Map listCategory(Map in) throws Exception {
        String categoryName = (String) in.get("categoryName");
        String pid = (String) in.get("pid");

        Map qIn = new HashMap();
        qIn.put("categoryName", categoryName);
        qIn.put("pid", pid);
        ArrayList categoryList = iCategoryService.listCategory(qIn);

        Map out = new HashMap();
        out.put("categoryList", categoryList);
        return out;
    }

    @Override
    public Map getCategory(Map in) throws Exception {
        String categoryName = (String) in.get("categoryName");
        String categoryId = (String) in.get("categoryId");

        Category category = null;
        if (categoryId != null) {
            category = iCategoryService.getCategoryById(categoryId);
        } else {
            if (categoryName != null) {
                category = iCategoryService.getCategoryByName(categoryName);
            }
        }

        Map out = new HashMap();
        out.put("category", category);
        return out;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCategory(Map in) throws Exception {
        String categoryName=(String)in.get("categoryName");
        String categoryId=in.get("categoryId").toString();
        String pid=(String)in.get("pid");
        String pname=(String)in.get("pname");

        Category category=iCategoryService.getCategoryById(categoryId);
        if(!category.getCategoryName().equals(categoryName)){
            category.setCategoryName(categoryName);
        }
        if(pid!=null) {
            category.setPid(pid);
        }
        if(pname!=null){
            Category pCategory=iCategoryService.getCategoryByName(pname);
            category.setPid(pCategory.getCategoryId());
        }
        iCategoryService.updateCategory(category);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCategory(Map in) throws Exception {
        String categoryId=in.get("categoryId").toString();

        iCategoryService.deleteCategory(categoryId);
    }
}
