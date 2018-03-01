package sproject.base.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sproject.base.dto.DeptLevelDto;
import sproject.base.param.DeptParam;
import sproject.base.service.SysDeptService;
import sproject.base.service.SysTreeService;
import sproject.common.JsonData;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/sys/dept")
public class SysDeptController {

    @Resource
    private SysDeptService sysDeptService;

    @Resource
    private SysTreeService sysTreeService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveDept(DeptParam deptParam){
        sysDeptService.save(deptParam);
        return JsonData.success();
    }

    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonData tree(){
        List<DeptLevelDto> dtoList = sysTreeService.deptTree();
        return JsonData.success(dtoList);
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData update(DeptParam deptParam){
        sysDeptService.update(deptParam);
        return JsonData.success();
    }

}
