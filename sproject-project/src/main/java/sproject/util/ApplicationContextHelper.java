package sproject.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: rayman
 * @Description:获取spring容器中的bean
 * @Date: Create in 2018/2/28 11:06
 * @Modified By:
 */
@Component("applicationContextHelper")
public class ApplicationContextHelper implements ApplicationContextAware{
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }
    public static <T> T popBean(Class<T> clazz){
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(clazz);
    }

    public static <T> T popBean(String name,Class<T> clazz){
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(name, clazz);
    }
}
