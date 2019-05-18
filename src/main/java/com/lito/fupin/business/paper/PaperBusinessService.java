package com.lito.fupin.business.paper;

import com.lito.fupin.meta.paper.entity.Paper;
import com.lito.fupin.meta.paper.service.IPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service
public class PaperBusinessService implements IPaperBusinessService{
    private final IPaperService iPaperService;

    @Autowired
    public PaperBusinessService(IPaperService iPaperService) {
        this.iPaperService = iPaperService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map createPaper(Map in) throws Exception {
        Paper paper=new Paper();
        paper.setAuthor(author);
        paper.setCategoryId(categoryId);
        paper.setContent(content);
        paper.setFileUrl(fileUrl);
        paper.setImgUrl(imgUrl);
        paper.setIsPublic(isPublic);
        paper.setPaperId(paperId);
        paper.setTitle(title);
        paper.setUploadTime(new Date());
        paper.setUploadUserId();
        iPaperService.createPaper(paper);
        return null;
    }
}
