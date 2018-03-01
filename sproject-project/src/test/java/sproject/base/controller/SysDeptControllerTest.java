package sproject.base.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2018/3/1 16:52
 * @Modified By:
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml","classpath:spring/springmvc.xml"})
public class SysDeptControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private SysDeptController sysDeptController;

    @Before
    public void setUp(){
        mockMvc  = MockMvcBuilders.standaloneSetup(sysDeptController).build();
    }

    @Test
    @Transactional
    public void testHello() throws Exception {
        mockMvc.perform(post("/sys/dept/save.json")
                .characterEncoding("UTF-8")
                .param("name", "研发部")
                .param("seq", "1")
                .param("remark", "研发部"))
                .andExpect(jsonPath("$.ret").value("true"));
        mockMvc.perform(post("/sys/dept/save.json")
                .characterEncoding("UTF-8")
                .param("name", "研发部")
                .param("seq", "1")
                .param("remark", "研发部"))
                .andExpect(jsonPath("$.msg").value("同一层级下存在相同名称的部门"));

    }

}
