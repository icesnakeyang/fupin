package com.lito.fupin.meta.category.service;

import com.lito.fupin.meta.category.dao.CategoryDao;
import com.lito.fupin.meta.category.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Map;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createCategory(Category category) throws Exception {
        categoryDao.createCategory(category);
    }

    @Override
    public ArrayList<Category> listCategory(Map qIn) throws Exception {
        ArrayList<Category> categories = categoryDao.listCategory(qIn);
        return categories;
    }

    @Override
    public Category getCategoryByName(String name) throws Exception {
        Category category = categoryDao.getCategoryByName(name);
        return category;
    }

    @Override
    public Category getCategoryById(String id) throws Exception {
        Category category = categoryDao.getCategoryById(id);
        return category;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCategory(Category category) throws Exception {
        categoryDao.updateCategory(category);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCategory(String categoryId) throws Exception {
        categoryDao.deleteCategory(categoryId);
    }
}
