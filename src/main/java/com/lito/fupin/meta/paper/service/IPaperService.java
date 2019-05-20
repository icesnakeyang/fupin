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
}
