package com.lito.fupin.meta.paper.dao;

import com.lito.fupin.meta.paper.entity.Paper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface PaperDao {
    void createPaper(Paper paper);

    void listPaperUnApprove(Map in);
}
