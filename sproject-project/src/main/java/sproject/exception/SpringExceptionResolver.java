package sproject.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import sproject.common.JsonData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2018/2/26 18:20
 * @Modified By:
 */

/**
 * 统一异常处理器
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {
        String url = request.getRequestURL().toString();
        ModelAndView mv;
        String defaultMsg = "System error";
        //系统所有数据请求.json结尾，页面请求.page结尾
        if (url.endsWith(".json")) {
            if (ex instanceof PermissionExecption|| ex instanceof ParamException) {
                JsonData result = JsonData.fail(ex.getMessage());
                mv = new ModelAndView("jsonView", result.toMap());
            } else {
                log.error("unknown json exception, url:" + url, ex);
                JsonData result = JsonData.fail(defaultMsg);
                mv = new ModelAndView("jsonView", result.toMap());
            }
        } else if (url.endsWith(".page")) {
            log.error("unknown page exception, url:" + url, ex);
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("exception", result.toMap());
        } else {
            log.error("unknown exception, url:"+url,ex);
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("jsonView", result.toMap());
        }

        return mv;
    }
}
