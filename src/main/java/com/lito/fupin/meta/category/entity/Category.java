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
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public Integer getIds() {
        return ids;
    }

    public void setIds(Integer ids) {
        this.ids = ids;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
