package com.lito.fupin.controller.api.category;

import com.lito.fupin.business.category.ICategoryBusinessService;
import com.lito.fupin.controller.vo.Response;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final ICategoryBusinessService iCategoryBusinessService;

    Logger logger = LoggerFactory.getLogger(getClass());

    public CategoryController(ICategoryBusinessService iCategoryBusinessService) {
        this.iCategoryBusinessService = iCategoryBusinessService;
    }

    @ResponseBody
    @PostMapping("/createCategory")
    public Response createCategory(@RequestBody CategoryRequest request,
                                   HttpServletRequest httpServletRequest) {
        Response response = new Response();
        try {
            String token = httpServletRequest.getHeader("token");
            Map in = new HashMap();
            in.put("token", token);
            in.put("categoryName", request.getCategoryName());
            in.put("pid", request.getPid());
            iCategoryBusinessService.createCategory(in);
        } catch (Exception ex) {
            try {
                response.setCode(Integer.parseInt(ex.getMessage()));
            } catch (Exception ex2) {
                logger.error(ex.getMessage());
            }
        }
        return response;
    }

    @ResponseBody
    @PostMapping("/listCategory")
    public Response listCategory(@RequestBody CategoryRequest request,
                                 HttpServletRequest httpServletRequest) {
        Response response = new Response();
        try {
            Map in = new HashMap();
            in.put("categoryName", request.getCategoryName());
            in.put("pid", request.getPid());
            Map out = iCategoryBusinessService.listCategory(in);
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
    @PostMapping("/getCategory")
    public Response getCategory(@RequestBody CategoryRequest request,
                                HttpServletRequest httpServletRequest) {
        Response response = new Response();
        try {
            Map in = new HashMap();
            in.put("categoryName", request.getCategoryName());
            in.put("categoryId", request.getCategoryId());
            Map out = iCategoryBusinessService.getCategory(in);
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
    @PostMapping("/updateCategory")
    public Response updateCategory(@RequestBody CategoryRequest request,
                                   HttpServletRequest httpServletRequest) {
        Response response = new Response();
        try {
            String token = httpServletRequest.getHeader("token");
            Map in = new HashMap();
            in.put("token", token);
            in.put("categoryName", request.getCategoryName());
            in.put("categoryId", request.getCategoryId());
            in.put("pid", request.getPid());
            iCategoryBusinessService.updateCategory(in);
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
    @PostMapping("/deleteCategory")
    public Response deleteCategory(@RequestBody CategoryRequest request,
                                   HttpServletRequest httpServletRequest) {
        Response response = new Response();
        try {
            String token=httpServletRequest.getHeader("token");
            Map in = new HashMap();
            in.put("token", token);
            in.put("categoryId", request.getCategoryId());
            iCategoryBusinessService.deleteCategory(in);
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
