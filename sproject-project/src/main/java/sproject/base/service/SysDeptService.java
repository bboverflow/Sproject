package sproject.base.service;

import com.google.common.base.Preconditions;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sproject.base.dao.SysDeptMapper;
import sproject.base.dao.SysUserMapper;
import sproject.base.model.SysDept;
import sproject.base.param.DeptParam;
import sproject.exception.ParamException;
import sproject.util.BeanValidator;
import sproject.util.LevelUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysDeptService {
    @Resource
    private SysDeptMapper sysDeptMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 新增部门
     * @param param
     */
    public void save(DeptParam param){
        BeanValidator.check(param);
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept dept = SysDept.builder().name(param.getName()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();

        Integer parentId = param.getParentId();
        dept.setLevel(LevelUtil.calculateLevel(getLevelbyId(parentId), parentId));
        //TODO
        dept.setOperator("system");
        dept.setOperateIp("127.0.0.1");
        dept.setOperateTime(new Date());
        sysDeptMapper.insertSelective(dept);
    }

    /**
     * 更新部门
     * @param param
     */
    public void update(DeptParam param) {
        BeanValidator.check(param);
        //原部门是否存在
        SysDept before = sysDeptMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的部门不存在");
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept after = SysDept.builder().id(param.getId()).name(param.getName()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();
        after.setLevel(LevelUtil.calculateLevel(getLevelbyId(param.getParentId()), param.getParentId()));

        //TODO
        after.setOperator("system");
        after.setOperateIp("127.0.0.1");
        after.setOperateTime(new Date());


        updateWithChild(before, after);

    }

    /**
     * 更新部门及子部门
     * @param before
     * @param after
     */
    @Transactional(rollbackFor = Exception.class)
    void updateWithChild(SysDept before, SysDept after) {
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        //如果level发生变化，需要更新子部门
        if (!after.getLevel().equals(before.getLevel())) {
            List<SysDept> deptList = sysDeptMapper.getChildDeptListByLevel(before.getLevel());
            if (CollectionUtils.isNotEmpty(deptList)) {
                for (SysDept dept : deptList) {
                    String level = dept.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        dept.setLevel(level);
                    }
                }
                sysDeptMapper.batchUpdateLevel(deptList);
            }
        }
        sysDeptMapper.updateByPrimaryKey(after);
    }

    /**
     * 判断部门是否存在
     * @param parentId
     * @param deptName
     * @param deptId
     * @return
     */
    private boolean checkExist(Integer parentId,String deptName,Integer deptId){
        return sysDeptMapper.countByNameAndParentId(parentId, deptName, deptId) > 0;
    }

    /**
     * 根据部门id返回层级
     */
    private String getLevelbyId(Integer deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (dept == null) {
            return null;
        }
        return dept.getLevel();
    }

    public void delete(int deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        Preconditions.checkNotNull(dept, "待删除的部门不存在，无法删除");
        if (sysDeptMapper.countByParentId(dept.getId()) > 0) {
            throw new ParamException("当前部门下面有子部门，无法删除");
        }
        if(sysUserMapper.countByDeptId(dept.getId()) > 0) {
            throw new ParamException("当前部门下面有用户，无法删除");
        }
        sysDeptMapper.deleteByPrimaryKey(deptId);
    }


}
