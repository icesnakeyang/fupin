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
    public Paper getPaperDetailByPaperId(String paperId) throws Exception {
        Paper paper = paperDao.getPaperDetailByPaperId(paperId);
        return paper;
    }

    /**
     * 读取已通过审核的文章列表
     *
     * @param categoryId
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<Paper> listPaperByCategoryId(String categoryId, Integer pageIndex, Integer pageSize) throws Exception {
        Map qIn = new HashMap();
        qIn.put("categoryId", categoryId);
        Integer offset = pageIndex * pageSize;
        qIn.put("offset", offset);
        qIn.put("size", pageSize);
        ArrayList<Paper> paperList = paperDao.listPaper(qIn);
        return paperList;
    }

    @Override
    public ArrayList<Paper> listMyPendingPaper(String userId, Integer pageIndex, Integer pageSize) throws Exception {
        Map qIn = new HashMap();
        qIn.put("userId", userId);
        qIn.put("offset", pageIndex * pageSize);
        qIn.put("size", pageSize);
        ArrayList<Paper> paperList = paperDao.listMyPendingPaper(qIn);
        return paperList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateAddView(String paperId) throws Exception {
        paperDao.updateAddView(paperId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletePaper(String paperId) throws Exception {
        paperDao.deletePaper(paperId);
    }

    /**
     * 读取一个机构的所有文章
     *
     * @param organizeId
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<Paper> listPaperByOrganize(String organizeId) throws Exception {
        ArrayList<Paper> paperList = paperDao.listPaperByOrganize(organizeId);
        return paperList;
    }

    /**
     * 读取上一篇文章的标题信息
     * @param categoryId
     * @param ids
     * @return
     * @throws Exception
     */
    @Override
    public Paper getLastPaper(String categoryId, Integer ids) throws Exception {
        Map qIn = new HashMap();
        qIn.put("categoryId", categoryId);
        qIn.put("ids", ids);
        Paper paper = paperDao.getLastPaper(qIn);
        return paper;
    }

    /**
     * 读取下一篇文章的标题信息
     * @param categoryId
     * @param ids
     * @return
     * @throws Exception
     */
    @Override
    public Paper getNextPaper(String categoryId, Integer ids) throws Exception {
        Map qIn = new HashMap();
        qIn.put("categoryId", categoryId);
        qIn.put("ids", ids);
        Paper paper = paperDao.getNextPaper(qIn);
        return paper;
    }
}
