package com.lito.fupin.controller.user;

import com.lito.fupin.business.user.IUserBusinessService;
import com.lito.fupin.controller.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserBusinessService iUserBusinessService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public UserController(IUserBusinessService iUserBusinessService) {
        this.iUserBusinessService = iUserBusinessService;
    }

    /**
     * 注册一个新用户
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @ResponseBody
    @PostMapping("register")
    public Response register(@RequestBody UserRequest request,
                             HttpServletRequest httpServletRequest) {
        Response response = new Response();
        try {
            Map in = new HashMap();
            in.put("loginName", request.getLoginName());
            in.put("password", request.getPassword());
            in.put("organizeId", request.getOrganizeId());
            Map out = iUserBusinessService.register(in);
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
    @PostMapping("/login")
    public Response login(@RequestBody UserRequest request) {
        Response response = new Response();
        try {
            Map in = new HashMap();
            in.put("loginName", request.getLoginName());
            in.put("password", request.getPassword());
            Map out = iUserBusinessService.login(in);
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
