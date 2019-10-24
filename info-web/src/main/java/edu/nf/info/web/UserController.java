package edu.nf.info.web;

import com.google.gson.Gson;
import edu.nf.info.entity.Users;
import edu.nf.info.service.UserService;
import edu.nf.info.web.vo.ResponseVO;
import edu.nf.webmvc.core.UrlPattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author YXD
 * @date 2019/10/22
 */
public class UserController extends BaseController{
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @UrlPattern("/listUsers")
    public void listUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        ResponseVO vo=null;
        try {
            List<Users> list=userService.listUsers();
            vo=success(200,list);
        } catch (Exception e) {
            e.printStackTrace();
            vo=error(500,e.getMessage());
        }
        response.getWriter().println(new Gson().toJson(vo));
    }
}
