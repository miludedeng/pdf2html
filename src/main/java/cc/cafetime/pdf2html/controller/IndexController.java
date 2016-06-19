package cc.cafetime.pdf2html.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liujing on 16/6/19.
 */
@Controller
public class IndexController {

    @RequestMapping(value={"/","/index"},method= RequestMethod.GET)
    public String index(){
        return "upload";
    }
}
