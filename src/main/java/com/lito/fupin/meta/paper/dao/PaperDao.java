package com.lito.fupin.meta.paper.dao;

import com.lito.fupin.meta.paper.entity.Paper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Map;

@Mapper
public interface PaperDao {
    void createPaper(Paper paper);

    /**
     * 读取一个机构下的所有未审核文章
     * @param organizeId
     */
    ArrayList<Paper> listPaperUnApprove(String organizeId);
}
