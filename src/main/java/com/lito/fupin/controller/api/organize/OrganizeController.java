package com.lito.fupin.controller.api.organize;

import com.lito.fupin.business.organize.IOrganizeBusinessService;
import com.lito.fupin.controller.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/organize")
public class OrganizeController {
    private final IOrganizeBusinessService iOrganizeBusinessService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public OrganizeController(IOrganizeBusinessService iOrganizeBusinessService) {
        this.iOrganizeBusinessService = iOrganizeBusinessService;
    }

    @ResponseBody
    @PostMapping("/createOrganize")
    public Response createOrganize(@RequestBody OrganizeRequest request,
                                   HttpServletRequest httpServletRequest) {
        Response response = new Response();
        try {
            Map in = new HashMap();
            in.put("organizeName", request.getOrganizeName());
            in.put("pid", request.getPid());
            Map out = iOrganizeBusinessService.createOrganize(in);
            response.setData(out);
        } catch (Exception ex) {
            try {
                response.setCode(Integer.parseInt(ex.getMessage()));
            } catch (Exception ex2) {
                response.setCode(10001);
                logger.error(ex.getMessage());
            }
        }
        return response;
    }
}
