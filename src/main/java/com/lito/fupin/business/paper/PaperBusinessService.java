package com.lito.fupin.business.paper;

import com.lito.fupin.common.GGF;
import com.lito.fupin.common.ICommonService;
import com.lito.fupin.meta.category.entity.Category;
import com.lito.fupin.meta.organize.entity.Organize;
import com.lito.fupin.meta.organize.service.IOrganizeService;
import com.lito.fupin.meta.paper.entity.Paper;
import com.lito.fupin.meta.paper.service.IPaperService;
import com.lito.fupin.meta.user.entity.User;
import com.lito.fupin.meta.user.service.IUserService;
import com.sun.xml.internal.ws.policy.spi.PolicyAssertionValidator;
import org.omg.PortableInterceptor.ServerRequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
     *
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
         * 5、如果当前用户的机构无上级机构，即还需审核自己的文章，添加本级机构创建的文章
         */

        User loginUser = iUserService.getUserByToken(token);

        ArrayList<Organize> organizeList = iOrganizeService.listOrganizeByPid(loginUser.getOrganizeId());

        Organize organize = iOrganizeService.getOrganizeById(loginUser.getOrganizeId());
        if (organize.getPid() == null) {
            //没有父机构了，需要审核自己
            organizeList.add(organize);
        }

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
     *
     * @param in
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void approvePaper(Map in) throws Exception {
        String token = in.get("token").toString();
        String paperId = in.get("paperId").toString();
        String isPublic = (String) in.get("isPublic");
        String content = (String) in.get("content");
        String title = (String) in.get("title");
        String author = (String) in.get("author");
        String categoryId = (String) in.get("categoryId");

        //检查用户是否管理员
        User loginUser = iCommonService.checkUser(token, "stuff");

        Paper paper = iPaperService.getPaperTinyByPaperId(paperId);
        //读取当前用户的机构
        Organize userOrganize = iOrganizeService.getOrganizeById(loginUser.getOrganizeId());
        //读取当前文章的机构
        Organize paperOrganize = iOrganizeService.getOrganizeById(paper.getOrganizeId());

        if (paperOrganize.getPid() == null) {
            //如果当前文章的机构没有上级机构，则检查是否和当前用户是同一机构
            if (!paperOrganize.getOrganizeId().equals(loginUser.getOrganizeId())) {
                throw new Exception("10002");
            }
        } else {
            if (!paperOrganize.getPid().equals(userOrganize.getOrganizeId())) {
                //检查当前文章的机构的上级机构不是当前用户的机构
                throw new Exception("10002");
            }
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

    /**
     * 拒绝文章审核
     *
     * @param in
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void rejectPaper(Map in) throws Exception {
        String token = in.get("token").toString();
        String paperId = in.get("paperId").toString();
        String remark = (String) in.get("remark");

        User user = iUserService.getUserByToken(token);
        Paper paper = iPaperService.getPaperTinyByPaperId(paperId);
        Organize userOrganize = iOrganizeService.getOrganizeById(user.getOrganizeId());
        Organize paperOrganize = iOrganizeService.getOrganizeById(paper.getOrganizeId());
        if (!paperOrganize.getPid().equals(userOrganize.getOrganizeId())) {
            throw new Exception("10002");
        }
        paper.setStatus("拒绝审核");
        paper.setApproveTime(new Date());
        paper.setApproveUserId(user.getUserId());
        paper.setApproveRemark(remark);
        iPaperService.updatePaper(paper);
    }

    @Override
    public Map getPaperByPaerId(Map in) throws Exception {
//        String token=in.get("token").toString();
        String paperId = in.get("paperId").toString();

//        iCommonService.checkUser(token, "stuff");
        Paper paper = iPaperService.getPaperDetailByPaperId(paperId);
        iPaperService.updateAddView(paperId);
        Map out = new HashMap();
        out.put("paper", paper);
        return out;
    }

    /**
     * 读取已通过审核的文章列表
     *
     * @param in categoryId：分类
     * @return
     * @throws Exception
     */
    @Override
    public Map listPaperList(Map in) throws Exception {
        /**
         * 读取categoryId下的所有下级categoryId
         */
        String categoryId = in.get("categoryId").toString();
        Integer pageIndex = (Integer) in.get("pageIndex");
        Integer pageSize = (Integer) in.get("pageSize");

        ArrayList<Paper> papers = iPaperService.listPaperByCategoryId(categoryId, pageIndex, pageSize);
        Map out = new HashMap();
        out.put("list", papers);
        return out;
    }

    @Override
    public Map listMyPendingPaper(Map in) throws Exception {
        String token = in.get("token").toString();
        Integer pageIndex = (Integer) in.get("pageIndex");
        Integer pageSize = (Integer) in.get("pageSize");

        User loginUser = iCommonService.checkUser(token, "stuff");
        ArrayList<Paper> paperList = iPaperService.listMyPendingPaper(loginUser.getUserId(), pageIndex, pageSize);
        Map out = new HashMap();
        out.put("list", paperList);
        return out;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletePaper(Map in) throws Exception {
        String token = in.get("token").toString();
        String paperId = in.get("paperId").toString();

        User loginUser = iCommonService.checkUser(token, "stuff");
        iPaperService.deletePaper(paperId);
    }

    /**
     * 管理员读取所有下级机构和本级机构的文章
     *
     * @param in
     * @return
     * @throws Exception
     */
    @Override
    public Map listAllPaperSub(Map in) throws Exception {
        String token = in.get("token").toString();
        User loginUser = iCommonService.checkUser(token, "stuff");

        Organize organize=iOrganizeService.getOrganizeById(loginUser.getOrganizeId());

        ArrayList<Paper> paperList=new ArrayList<>();
        if(organize.getPid()==null){
            //没有父机构了，需要读取本级机构的文章
            paperList.addAll(iPaperService.listPaperByOrganize(organize.getOrganizeId()));
        }
        ArrayList<Organize> subOrganizeList=iOrganizeService.listOrganizeByPid(organize.getOrganizeId());
        for(int i=0;i<subOrganizeList.size();i++) {
            paperList.addAll(listSubPaper(subOrganizeList.get(i).getOrganizeId()));
        }

        Map out = new HashMap();
        out.put("paperList", paperList);

        return out;
    }

    /**
     * 递归读取一个机构及所有子机构的文章
     *
     * @param organizeId
     * @return
     * @throws Exception
     */
    private ArrayList<Paper> listSubPaper(String organizeId) throws Exception {
        ArrayList<Paper> paperList = iPaperService.listPaperByOrganize(organizeId);
        ArrayList<Organize> subOrganizeList = iOrganizeService.listOrganizeByPid(organizeId);
        for (int i = 0; i < subOrganizeList.size(); i++) {
            paperList.addAll(listSubPaper(subOrganizeList.get(i).getOrganizeId()));
        }
        return paperList;
    }
}
