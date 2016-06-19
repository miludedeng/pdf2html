package cc.cafetime.pdf2html.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liujing on 16/6/19.
 */

@Controller
public class LoginController {

    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value="/login",method=RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam String userName, @RequestParam String password , HttpServletRequest request){
        if("zving".equals(userName)&&"10301".equals(password)){
            request.getSession().setAttribute("login","Y");
            return "success";
        }
        return "fail";
    }
}
