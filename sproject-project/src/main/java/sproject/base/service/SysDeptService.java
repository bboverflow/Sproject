package sproject.base.service;

import org.springframework.stereotype.Service;
import sproject.base.dao.SysDeptMapper;
import sproject.base.model.SysDept;
import sproject.base.param.DeptParam;
import sproject.exception.ParamExecption;
import sproject.util.BeanValidator;

import javax.annotation.Resource;

@Service
public class SysDeptService {
    @Resource
    private SysDeptMapper sysDeptMapper;

    public void save(DeptParam param){
        BeanValidator.check(param);
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamExecption("同一层级下存在相同名称的部门");
        }
        SysDept dept = SysDept.builder().name(param.getName()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();


    }

    private boolean checkExist(Integer parentId,String deptName,Integer deptId){
        //TODO
        return true;
    }
}
