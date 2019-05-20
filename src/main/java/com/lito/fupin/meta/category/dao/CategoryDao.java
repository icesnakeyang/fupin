package com.lito.fupin.meta.category.dao;

import com.lito.fupin.meta.category.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Map;

@Mapper
public interface CategoryDao {
    void createCategory(Category category);

    ArrayList<Category> listCategory(Map qIn);

    Category getCategoryByName(String name);

    Category getCategoryById(String id);

    void updateCategory(Category category);

    void deleteCategory(String categoryId);
}
