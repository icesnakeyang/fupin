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
@RequestMapping("/api/organize")
public class OrganizeController {
    private final IOrganizeBusinessService iOrganizeBusinessService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public OrganizeController(IOrganizeBusinessService iOrganizeBusinessService) {
        this.iOrganizeBusinessService = iOrganizeBusinessService;
    }

    /**
     * 创建机构
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @ResponseBody
    @PostMapping("/createOrganize")
    public Response createOrganize(@RequestBody OrganizeRequest request,
                                   HttpServletRequest httpServletRequest) {
        Response response = new Response();
        try {
            String token = httpServletRequest.getHeader("token");
            Map in = new HashMap();
            in.put("token", token);
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

    /**
     * 根据机构名称模糊查询机构列表，支持分页
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @ResponseBody
    @PostMapping("/listOrganize")
    public Response listOrganize(@RequestBody OrganizeRequest request,
                                 HttpServletRequest httpServletRequest) {
        Response response = new Response();
        try {
            String token = httpServletRequest.getHeader("token");
            Map in = new HashMap();
            in.put("token", token);
            in.put("organizeName", request.getOrganizeName());
            in.put("pageIndex", request.getPageIndex());
            in.put("pageSize", request.getPageSize());
            Map out = iOrganizeBusinessService.listOrganize(in);
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

    @ResponseBody
    @PostMapping("/updateOrganize")
    public Response updateOrganize(@RequestBody OrganizeRequest request,
                                   HttpServletRequest httpServletRequest) {
        Response response = new Response();
        try {
            String token=httpServletRequest.getHeader("token");
            Map in = new HashMap();
            in.put("token", token);
            in.put("organizeId", request.getOrganizeId());
            in.put("organizeName", request.getOrganizeName());
            in.put("pOrgName", request.getpOrgName());
            in.put("pid", request.getPid());
            iOrganizeBusinessService.updateOrganize(in);
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

    @ResponseBody
    @PostMapping("/deleteOrganize")
    public Response deleteOrganize(@RequestBody OrganizeRequest request,
                                   HttpServletRequest httpServletRequest) {
        Response response = new Response();
        try {
            String token=httpServletRequest.getHeader("token");
            Map in = new HashMap();
            in.put("token", token);
            in.put("organizeId", request.getOrganizeId());
            iOrganizeBusinessService.deleteOrganize(in);
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

    /**
     * 根据用户的token，查询用户权限下的所有机构
     *
     * @param httpServletRequest
     * @return
     */
    @ResponseBody
    @PostMapping("/listOrganizeByToken")
    public Response listOrganizeByToken(HttpServletRequest httpServletRequest) {
        Response response = new Response();
        try {
            String token = httpServletRequest.getHeader("token");
            Map in = new HashMap();
            in.put("token", token);

            Map out = iOrganizeBusinessService.listOrganizeByToken(in);
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
