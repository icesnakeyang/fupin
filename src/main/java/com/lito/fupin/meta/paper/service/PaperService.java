package com.lito.fupin.meta.paper.service;

import com.lito.fupin.meta.paper.dao.PaperDao;
import com.lito.fupin.meta.paper.entity.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaperService implements IPaperService{
    private final PaperDao paperDao;

    @Autowired
    public PaperService(PaperDao paperDao) {
        this.paperDao = paperDao;
    }

    @Override
    public void createPaper(Paper paper) throws Exception {
        paperDao.createPaper(paper);
    }
}
