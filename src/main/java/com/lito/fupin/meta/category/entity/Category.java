package com.lito.fupin.meta.category.entity;

import lombok.Data;

/**
 * 文章分类
 */
@Data
public class Category {
    private Integer ids;
    private String categoryId;
    private String categoryName;
    private String pid;
    private String pName;
}
