package com.lito.fupin.business.paper;

import java.util.Map;

public interface IPaperBusinessService {
    Map createPaper(Map in) throws Exception;

    Map listPaperUnApprove(Map in) throws Exception;
}
