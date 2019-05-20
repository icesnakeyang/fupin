package com.lito.fupin.meta.category.service;

import com.lito.fupin.meta.category.entity.Category;

import java.util.ArrayList;
import java.util.Map;

public interface ICategoryService {
    void createCategory(Category category) throws Exception;
    ArrayList<Category> listCategory(Map qIn) throws Exception;
    Category getCategoryByName(String name) throws Exception;
    Category getCategoryById(String id) throws Exception;
    void updateCategory(Category category) throws Exception;
    void deleteCategory(String categoryId) throws Exception;
}
