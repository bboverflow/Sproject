package sproject.base.dto;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import sproject.base.model.SysDept;

import java.util.List;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2018/3/1 11:38
 * @Modified By:
 */
@Getter
@Setter
public class DeptLevelDto extends SysDept{

    private List<DeptLevelDto> deptList = Lists.newArrayList();

    public static DeptLevelDto adapt(SysDept dept) {
        DeptLevelDto dto = new DeptLevelDto();
        BeanUtils.copyProperties(dept,dto);
        return dto;
    }
}
