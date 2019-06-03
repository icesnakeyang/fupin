package com.lito.fupin.business.paper;

import com.lito.fupin.meta.paper.entity.Paper;

import java.util.Map;

public interface IPaperBusinessService {
    Map createPaper(Map in) throws Exception;

    /**
     * 读取一个用户需要审核的下级单位文章
     * @param in
     * @return
     * @throws Exception
     */
    Map listPaperUnApprove(Map in) throws Exception;

    void approvePaper(Map in) throws Exception;

    void rejectPaper(Map in) throws Exception;

    /**
     * 读取文章详细信息
     * @param in
     * @return
     * @throws Exception
     */
    Map getPaperByPaerId(Map in) throws Exception;

    Map listPaperList(Map in) throws Exception;

    Map listMyPendingPaper(Map in) throws Exception;

    void deletePaper(Map in) throws Exception;

    Map listAllPaperSub(Map in) throws Exception;

    void editPaper(Map in) throws Exception;

    /**
     * 读取上一条文章的简要信息
     * @param paperId
     * @return
     * @throws Exception
     */
    Paper getLastPaper(String paperId) throws Exception;

    Paper getNextPaper(String paperId) throws Exception;
}
