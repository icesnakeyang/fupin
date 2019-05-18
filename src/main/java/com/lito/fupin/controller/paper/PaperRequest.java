package com.lito.fupin.controller.paper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperRequest {
    private Integer ids;
    private String paperId;
    private String title;
    private String content;
    private String categoryId;
    private String isPublic;
    private String imgUrl;
    private String fileUrl;
    private String author;
}
