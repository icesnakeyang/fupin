package com.lito.fupin.business.paper;

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
}
