package sproject.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2018/2/7 16:02
 * @Modified By:
 */
@Controller
public class PageController {
    @RequestMapping(value = {"/","root"})
    public String hello(){
        return "index";
    }
}
