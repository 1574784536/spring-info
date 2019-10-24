package edu.nf.info.web;

import edu.nf.info.web.vo.ResponseVO;

import javax.servlet.http.HttpServlet;

/**
 * @author YXD
 * @date 2019/10/23
 */
public class BaseController extends HttpServlet {
    protected ResponseVO success(int code,Object data){
        ResponseVO vo=new ResponseVO();
        vo.setCode(code);
        vo.setData(data);
        return vo;
    }
    protected ResponseVO success(int code){
        ResponseVO vo=new ResponseVO();
        vo.setCode(code);
        return vo;
    }

    protected ResponseVO error(int code,String message){
        ResponseVO vo=new ResponseVO();
        vo.setCode(code);
        vo.setData(message);
        return vo;
    }
}
