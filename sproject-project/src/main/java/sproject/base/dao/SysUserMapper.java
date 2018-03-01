package sproject.base.dao;

import org.apache.ibatis.annotations.Param;
import sproject.base.model.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    int countByDeptId(@Param("deptId") int deptId);
}