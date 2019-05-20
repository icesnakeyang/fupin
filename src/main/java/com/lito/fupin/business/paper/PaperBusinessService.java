package com.lito.fupin.business.paper;

import com.lito.fupin.common.GGF;
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

    @Autowired
    public PaperBusinessService(IPaperService iPaperService,
                                IUserService iUserService,
                                IOrganizeService iOrganizeService) {
        this.iPaperService = iPaperService;
        this.iUserService = iUserService;
        this.iOrganizeService = iOrganizeService;
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
        iPaperService.createPaper(paper);

        Map out = new HashMap();
        out.put("paper", paper);
        return out;
    }

    @Override
    public Map listPaperUnApprove(Map in) throws Exception {
        String token=in.get("token").toString();
        User user=iUserService.getUserByToken(token);

        /**
         * 1、读取当前用户
         * 2、获取当前用户的机构id
         * 3、读取该机构id下一级的所有的机构id
         * 4、查询这些机构id下的所有文章，Approve time=null
         */

        User loginUser=iUserService.getUserByToken(token);

        ArrayList<Organize> organizeList=iOrganizeService.listOrganizeByPid(loginUser.getOrganizeId());


        for(int i=0;i<organizeList.size();i++){
            iPaperService.createPaper();
        }
        return null;
    }
}
