package sproject.sys.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2018/2/27 18:16
 * @Modified By:
 */
@Getter
@Setter
public class TestVo {
    @NotBlank
    private String msg;

    @NotNull
    private Integer id;

/*
    @NotEmpty
    private List<String> str;
*/
}
