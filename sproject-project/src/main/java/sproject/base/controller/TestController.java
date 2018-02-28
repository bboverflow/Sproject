package sproject.base.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sproject.base.dao.SysAclModuleMapper;
import sproject.base.model.SysAclModule;
import sproject.common.JsonData;
import sproject.common.JsonMapper;
import sproject.exception.ParamExecption;
import sproject.param.TestVo;
import sproject.util.ApplicationContextHelper;
import sproject.util.BeanValidator;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2018/2/27 15:52
 * @Modified By:
 */
@Slf4j
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello(){
        log.info("hello");
        throw new RuntimeException("test execption");
//        return JsonData.success("hello, permission");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestVo vo) throws ParamExecption {
        BeanValidator.check(vo);
        return JsonData.success("test validate");
    }

    @RequestMapping("/getBean.json")
    @ResponseBody
    public JsonData getBean() throws ParamExecption{
        SysAclModuleMapper moduleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
        SysAclModule sysAclModule = moduleMapper.selectByPrimaryKey(1);
        log.info(JsonMapper.obj2String(sysAclModule));
        return JsonData.success(sysAclModule);
    }
}
