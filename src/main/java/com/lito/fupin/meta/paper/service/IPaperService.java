package com.lito.fupin.meta.paper.service;

import com.lito.fupin.meta.paper.entity.Paper;

import java.util.ArrayList;
import java.util.Map;

public interface IPaperService {
    void createPaper(Paper paper) throws Exception;

    /**
     * 读取一个机构下的所有未审核文章
     *
     * @param organizeId
     * @return
     * @throws Exception
     */
    ArrayList<Paper> listPaperUnApprove(String organizeId) throws Exception;

    /**
     * 读取文章的简要信息，不包含详情
     * @param paperId
     * @return
     * @throws Exception
     */
    Paper getPaperTinyByPaperId(String paperId) throws Exception;

    /**
     * 增量修改文章信息
     * @param paper
     * @throws Exception
     */
    void updatePaper(Paper paper) throws Exception;

    ArrayList<Paper> listPaperToShow(Map in) throws Exception;
}
