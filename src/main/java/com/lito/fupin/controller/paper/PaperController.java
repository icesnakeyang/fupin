package com.lito.fupin.controller.paper;

import com.lito.fupin.business.paper.IPaperBusinessService;
import com.lito.fupin.controller.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/paper")
public class PaperController {
    private final IPaperBusinessService iPaperBusinessService;

    Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    public PaperController(IPaperBusinessService iPaperBusinessService) {
        this.iPaperBusinessService = iPaperBusinessService;
    }

    public Response createPaper(@RequestBody PaperRequest request,
                                HttpServletRequest httpServletRequest){
        Response response=new Response();
        try {
            Map in=new HashMap();
            in.put("title", request.getTitle());
            in.put("content", request.getContent());
            in.put("categoryId", request.getCategoryId());
            in.put("isPublic", request.getIsPublic());
            in.put("imgUrl", request.getImgUrl());
            in.put("fileUrl", request.getFileUrl());
            in.put("author", request.getAuthor());

            Map out=iPaperBusinessService.createPaper(in);
            response.setData(out);
        }catch (Exception ex){
            try {
                response.setCode(Integer.parseInt(ex.getMessage()));
            }catch (Exception ex2){
                response.setCode(10001);
                logger.error(ex.getMessage());
            }
        }
        return response;
    }
}
