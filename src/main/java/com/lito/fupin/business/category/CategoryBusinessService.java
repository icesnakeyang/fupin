package com.lito.fupin.business.category;

import com.lito.fupin.common.GGF;
import com.lito.fupin.common.ICommonService;
import com.lito.fupin.meta.category.entity.Category;
import com.lito.fupin.meta.category.service.ICategoryService;
import com.lito.fupin.meta.user.entity.User;
import com.lito.fupin.meta.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.swing.StringUIClientPropertyKey;

import javax.print.attribute.HashAttributeSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryBusinessService implements ICategoryBusinessService {
    private final ICategoryService iCategoryService;
    private final IUserService iUserService;
    private final ICommonService iCommonService;

    public CategoryBusinessService(ICategoryService iCategoryService,
                                   IUserService iUserService,
                                   ICommonService iCommonService) {
        this.iCategoryService = iCategoryService;
        this.iUserService = iUserService;
        this.iCommonService = iCommonService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createCategory(Map in) throws Exception {
        String token = in.get("token").toString();
        String categoryName = in.get("categoryName").toString();
        String pid = (String) in.get("pid");

        User loginUser = iUserService.getUserByToken(token);
        if (loginUser == null) {
            throw new Exception("10003");
        }
        if (!loginUser.getPermission().equals("网站管理员") &&
                !loginUser.getPermission().equals("普通管理员") &&
                !loginUser.getPermission().equals("超级管理员")) {
            throw new Exception("10005");
        }

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
    public Map listSubCategory(Map in) throws Exception {
        String categoryName = (String) in.get("categoryName");
        String pid = (String) in.get("pid");

        if (pid == null) {
            Category category = iCategoryService.getCategoryByName(categoryName);
            pid = category.getCategoryId();
        }
        Map qIn = new HashMap();
        qIn.put("pid", pid);
        ArrayList<Category> categories = iCategoryService.listCategory(qIn);

        Map out = new HashMap();
        out.put("categoryList", categories);
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
        String token = in.get("token").toString();
        String categoryName = (String) in.get("categoryName");
        String categoryId = in.get("categoryId").toString();
        String pid = (String) in.get("pid");
        String pname = (String) in.get("pname");

        User loginUser = iUserService.getUserByToken(token);
        if (loginUser == null) {
            throw new Exception("10003");
        }
        if (!loginUser.getPermission().equals("超级管理员") &&
                !loginUser.getPermission().equals("网站管理员") &&
                !loginUser.getPermission().equals("普通管理员")) {
            throw new Exception("10005");
        }

        Category category = iCategoryService.getCategoryById(categoryId);
        if (!category.getCategoryName().equals(categoryName)) {
            category.setCategoryName(categoryName);
        }
        if (pid != null) {
            category.setPid(pid);
        }
        if (pname != null) {
            Category pCategory = iCategoryService.getCategoryByName(pname);
            category.setPid(pCategory.getCategoryId());
        }
        iCategoryService.updateCategory(category);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCategory(Map in) throws Exception {
        String token = in.get("token").toString();
        String categoryId = in.get("categoryId").toString();
        iCommonService.checkUser(token, "stuff");
        iCategoryService.deleteCategory(categoryId);
    }
}
