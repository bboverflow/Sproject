package sproject.base.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sproject.base.param.DeptParam;
import sproject.common.JsonData;

@Controller
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController {
    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveDept(DeptParam){

    }
}
