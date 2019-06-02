package com.lito.fupin.business.category;

import java.util.Map;

public interface ICategoryBusinessService {
    void createCategory(Map in) throws Exception;

    Map listCategory(Map in) throws Exception;

    Map listSubCategory(Map in) throws Exception;

    Map getCategory(Map in) throws Exception;

    void updateCategory(Map in) throws Exception;

    void deleteCategory(Map in) throws Exception;
}
