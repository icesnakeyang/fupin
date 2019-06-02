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
     *
     * @param organizeId
     */
    ArrayList<Paper> listPaperUnApprove(String organizeId);

    /**
     * 读取文章的简要信息，不包含详情
     *
     * @param paperId
     * @return
     */
    Paper getPaperTinyByPaperId(String paperId);

    /**
     * 增量修改文章信息
     *
     * @param paper
     */
    void updatePaper(Paper paper);

    Paper getPaperDetailByPaperId(String paperId);

    /**
     * 查询已通过审核的文章
     *
     * @param qIn categoryId
     *            organizeId
     *            offset *
     *            size *
     * @return
     */
    ArrayList<Paper> listPaper(Map qIn);

    ArrayList<Paper> listMyPendingPaper(Map qIn);

    void updateAddView(String paperId);

    void deletePaper(String paperId);

    /**
     * 读取一个机构的所有文章
     * @param organizeId
     * @return
     */
    ArrayList<Paper> listPaperByOrganize(String organizeId);
}
