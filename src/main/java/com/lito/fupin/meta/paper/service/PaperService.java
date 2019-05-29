package com.lito.fupin.meta.paper.service;

import com.lito.fupin.meta.paper.dao.PaperDao;
import com.lito.fupin.meta.paper.entity.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     * @param paperId
     * @return
     * @throws Exception
     */
    @Override
    public Paper getPaperTinyByPaperId(String paperId) throws Exception {
        Paper paper = paperDao.getPaperTinyByPaperId(paperId);
        return paper;
    }

    /**
     * 增量修改文章信息
     *
     * @param paper
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePaper(Paper paper) throws Exception {
        paperDao.updatePaper(paper);
    }

    @Override
    public ArrayList<Paper> listPaperToShow(Map in) throws Exception {
        String categoryId = (String) in.get("categoryId");
        String organizeId = (String)in.get("organizeId");
        Integer pageIndex = (Integer) in.get("pageIndex");
        Integer pageSize = (Integer) in.get("pageSize");
        Integer offset = pageIndex * pageSize;
        Integer size = pageSize;

        Map qIn = new HashMap();
        qIn.put("categoryId", categoryId);
        qIn.put("organizeId", organizeId);
        qIn.put("offset", offset);
        qIn.put("size", size);
        ArrayList<Paper> papers = paperDao.listPaperToShow(qIn);
        return papers;
    }

    @Override
    public Paper getPaperDetailByPaperId(String paperId) throws Exception {
        Paper paper = paperDao.getPaperDetailByPaperId(paperId);
        return paper;
    }
}
