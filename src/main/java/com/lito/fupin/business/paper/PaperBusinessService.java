package com.lito.fupin.business.paper;

import com.lito.fupin.common.GGF;
import com.lito.fupin.common.ICommonService;
import com.lito.fupin.meta.organize.entity.Organize;
import com.lito.fupin.meta.organize.service.IOrganizeService;
import com.lito.fupin.meta.paper.entity.Paper;
import com.lito.fupin.meta.paper.service.IPaperService;
import com.lito.fupin.meta.user.entity.User;
import com.lito.fupin.meta.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaperBusinessService implements IPaperBusinessService {
    private final IPaperService iPaperService;
    private final IUserService iUserService;
    private final IOrganizeService iOrganizeService;
    private final ICommonService iCommonService;

    @Autowired
    public PaperBusinessService(IPaperService iPaperService,
                                IUserService iUserService,
                                IOrganizeService iOrganizeService,
                                ICommonService iCommonService) {
        this.iPaperService = iPaperService;
        this.iUserService = iUserService;
        this.iOrganizeService = iOrganizeService;
        this.iCommonService = iCommonService;
    }

    /**
     * 创建一个文章paper
     *
     * @param in
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map createPaper(Map in) throws Exception {
        String token = in.get("token").toString();
        String author = (String) in.get("author");
        String categoryId = in.get("categoryId").toString();
        String content = in.get("content").toString();
        String fileUrl = (String) in.get("fileUrl");
        String imgUrl = (String) in.get("imgUrl");
        String isPublic = in.get("isPublic").toString();
        String title = in.get("title").toString();

        User user = iUserService.getUserByToken(token);
        Paper paper = new Paper();
        paper.setAuthor(author);
        paper.setCategoryId(categoryId);
        paper.setContent(content);
        paper.setFileUrl(fileUrl);
        paper.setImgUrl(imgUrl);
        paper.setIsPublic(isPublic);
        paper.setPaperId(GGF.UUID().toString());
        paper.setTitle(title);
        paper.setUploadTime(new Date());
        paper.setUploadUserId(user.getUserId());
        paper.setOrganizeId(user.getOrganizeId());
        paper.setStatus("等待审核");
        iPaperService.createPaper(paper);

        Map out = new HashMap();
        out.put("paper", paper);
        return out;
    }

    /**
     * 读取一个用户需要审核的下级单位文章
     * @param in
     * @return
     * @throws Exception
     */
    @Override
    public Map listPaperUnApprove(Map in) throws Exception {
        String token = in.get("token").toString();

        /**
         * 1、读取当前用户
         * 2、获取当前用户的机构id
         * 3、读取该机构id下一级的所有的机构id
         * 4、查询这些机构id下的所有文章，Approve time=null
         */

        User loginUser = iUserService.getUserByToken(token);

        ArrayList<Organize> organizeList = iOrganizeService.listOrganizeByPid(loginUser.getOrganizeId());

        ArrayList<Paper> paperList = new ArrayList<>();
        for (int i = 0; i < organizeList.size(); i++) {
            ArrayList<Paper> papers = iPaperService.listPaperUnApprove(organizeList.get(i).getOrganizeId());
            if (papers.size() > 0) {
                paperList.addAll(papers);
            }
        }

        Map out = new HashMap();
        out.put("paperList", paperList);
        return out;
    }

    /**
     * 通过审核
     * @param in
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void approvePaper(Map in) throws Exception {
        String token=in.get("token").toString();
        String paperId=in.get("paperId").toString();
        String isPublic=(String)in.get("isPublic");
        String content=(String)in.get("content");
        String title=(String)in.get("title");
        String author=(String)in.get("author");
        String categoryId=(String)in.get("categoryId");

        User loginUser=iCommonService.checkUser(token, "stuff");
        Paper paper=iPaperService.getPaperDetailByPaperId(paperId);
        Organize userOrganize=iOrganizeService.getOrganizeById(loginUser.getOrganizeId());
        Organize paperOrganize=iOrganizeService.getOrganizeById(paper.getOrganizeId());
        if(!paperOrganize.getPid().equals(userOrganize.getOrganizeId())){
            throw new Exception("10002");
        }
        paper.setTitle(title);
        paper.setContent(content);
        paper.setAuthor(author);
        paper.setIsPublic(isPublic);
        paper.setCategoryId(categoryId);
        paper.setStatus("通过审核");
        paper.setApproveTime(new Date());
        paper.setApproveUserId(loginUser.getUserId());
        iPaperService.updatePaper(paper);
    }

    @Override
    public Map listPaperToShow(Map in) throws Exception {
        ArrayList<Paper> papers=iPaperService.listPaperToShow(in);
        Map out=new HashMap();
        out.put("paperList", papers);
        return out;
    }

    /**
     * 拒绝文章审核
     * @param in
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void rejectPaper(Map in) throws Exception {
        String token=in.get("token").toString();
        String paperId=in.get("paperId").toString();
        String remark=(String)in.get("remark");

        User user=iUserService.getUserByToken(token);
        Paper paper=iPaperService.getPaperTinyByPaperId(paperId);
        Organize userOrganize=iOrganizeService.getOrganizeById(user.getOrganizeId());
        Organize paperOrganize=iOrganizeService.getOrganizeById(paper.getOrganizeId());
        if(!paperOrganize.getPid().equals(userOrganize.getOrganizeId())){
            throw new Exception("10002");
        }
        paper.setStatus("拒绝审核");
        paper.setApproveTime(new Date());
        paper.setApproveUserId(user.getUserId());
        paper.setApproveRemark(remark);
        iPaperService.updatePaper(paper);
    }

    @Override
    public Map getPaperByPaerid(Map in) throws Exception {
//        String token=in.get("token").toString();
        String paperId=in.get("paperId").toString();

//        iCommonService.checkUser(token, "stuff");
        Paper paper=iPaperService.getPaperDetailByPaperId(paperId);
        Map out=new HashMap();
        out.put("paper", paper);
        return out;
    }

    @Override
    public Map listPaperList(Map in) throws Exception {
        /**
         * 2、获取当前用户的机构id
         * 4、查询这些机构id下的所有文章
         */

        User loginUser = iUserService.getUserByToken(token);

        ArrayList<Organize> organizeList = iOrganizeService.listOrganizeByPid(loginUser.getOrganizeId());

        ArrayList<Paper> paperList = new ArrayList<>();
        for (int i = 0; i < organizeList.size(); i++) {
            ArrayList<Paper> papers = iPaperService.listPaperUnApprove(organizeList.get(i).getOrganizeId());
            if (papers.size() > 0) {
                paperList.addAll(papers);
            }
        }

        Map out = new HashMap();
        out.put("paperList", paperList);
        return out;
    }
}
