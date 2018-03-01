package sproject.base.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2018/3/1 10:45
 * @Modified By:
 */
@Slf4j
public class ApacheCommonTest {
    @Test
    public void testJoin(){
        String src = "0.1.2";
        String result = StringUtils.join(src, ".", 5);
        log.info(result);
    }
}
