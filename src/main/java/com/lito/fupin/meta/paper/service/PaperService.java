package com.lito.fupin.meta.paper.service;

import com.lito.fupin.meta.paper.dao.PaperDao;
import com.lito.fupin.meta.paper.entity.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;


@Service
public class PaperService implements IPaperService {
    private final PaperDao paperDao;

    @Autowired
    public PaperService(PaperDao paperDao) {
        this.paperDao = paperDao;
    }

    @Override
    public void createPaper(Paper paper) throws Exception {
        paperDao.createPaper(paper);
    }

    /**
     * 读取一个机构下的所有未审核文章
     *
     * @param organizeId
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<Paper> listPaperUnApprove(String organizeId) throws Exception {
        ArrayList<Paper> papers = paperDao.listPaperUnApprove(organizeId);
        return papers;
    }
}
