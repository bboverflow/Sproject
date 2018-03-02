package sproject.sys.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2018/2/28 11:25
 * @Modified By:
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml","classpath:spring/springmvc.xml"})
public class TestControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private TestController testController;

    @Before
    public void setUp(){
        mockMvc  = MockMvcBuilders.standaloneSetup(testController).build();
    }

    @Test(expected = NestedServletException.class)
    public void testHello() throws Exception {
        mockMvc.perform(post("/test/hello.json")
                .characterEncoding("UTF-8"))
                .andExpect(jsonPath("$.ret").value("true"));
    }

    @Test(expected = NestedServletException.class)
    public void testvalidateError() throws Exception {
        mockMvc.perform(post("/test/validate.json")
                .characterEncoding("UTF-8")
                .param("msg", "hello"))
                .andExpect(jsonPath("$.ret").value("true"));
    }

    @Test
    public void testvalidateSuccess() throws Exception {
        mockMvc.perform(post("/test/validate.json")
                .characterEncoding("UTF-8")
                .param("id","1")
                .param("msg", "hello"))
                .andExpect(jsonPath("$.ret").value("true"));
    }

    @Test
    public void testGetBean() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/test/getBean.json")
                .characterEncoding("UTF-8"));
        resultActions.andExpect(jsonPath("$.data.name").value("产品管理"));
/*
        MvcResult mvcResult = resultActions.andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
*/

    }
}
