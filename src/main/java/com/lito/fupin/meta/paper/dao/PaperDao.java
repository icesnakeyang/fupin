package com.lito.fupin.meta.paper.dao;

import com.lito.fupin.meta.paper.entity.Paper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaperDao {
    void createPaper(Paper paper);
}
