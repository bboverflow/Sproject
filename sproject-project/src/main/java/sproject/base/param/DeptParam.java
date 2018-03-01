package sproject.base.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class DeptParam {
    private Integer id;
    @NotBlank(message = "部门名称不可以为空")
    @Length(max=15,min=2,message = "部门名称需要在2-15个字之间")
    private String name;
    /**
     *保证parentid不为null
     */
    private Integer parentId = 0;
    @NotNull(message = "展示顺序不可以为空")
    private Integer seq;
    @Length(max=150,message = "备注的长度需要小于150字")
    private String remark;
}
