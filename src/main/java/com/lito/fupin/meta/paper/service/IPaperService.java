package com.lito.fupin.meta.paper.service;

import com.lito.fupin.meta.paper.entity.Paper;

import java.util.ArrayList;
import java.util.Map;

public interface IPaperService {
    void createPaper(Paper paper) throws Exception;
    ArrayList<Paper> listPaperUnApprove(Map in) throws Exception;
}
