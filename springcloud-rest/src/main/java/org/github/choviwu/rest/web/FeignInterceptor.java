package org.github..rest.web;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.github..common.util.HttpContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author
 * @date 2018/8/8
 * Description :
 */
public class FeignInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate template) {

        HttpServletRequest request = null;
        //当前feign远程调用环境不是由http接口发起，例如test单元测试中的feign调用或者项目启动后的feign调用
        try {
            request = HttpContext.getRequest();
        } catch (NullPointerException e) {
            //被调环境中不存在request对象，则不往header里添加当前请求环境的header
            return;
        }
        Enumeration<String> elements = request.getHeaderNames();
        if (elements != null) {
            while (elements.hasMoreElements()) {
                String name = elements.nextElement();
                String value = request.getHeader(name);
                //添加header
                template.header(name, value);
            }
        }
    }
}
