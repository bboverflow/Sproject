package sproject.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

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
