package com.lito.fupin.meta.paper.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Paper {
    private Integer ids;
    private String paperId;
    private String title;
    private String content;
    private Date uploadTime;
    private String uploadUserId;
    private String status;
    private String approveRemark;
    private Date approveTime;
    private String categoryId;
    private String isPublic;
    private String imgUrl;
    private String fileUrl;
    private String approveUserId;
    private String author;
    private Integer views;
    private String organizeId;
    private String organizeName;
}
