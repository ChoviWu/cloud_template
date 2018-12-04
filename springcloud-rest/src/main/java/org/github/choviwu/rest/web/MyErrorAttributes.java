package org.github..rest.web;


import com.xiaoleilu.hutool.bean.BeanUtil;
import org.github..api.web.ApiResponse;
import org.github..common.exception.ExceptionMessage;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author
 * @date 2018/8/8
 * Description :
 */
public class MyErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
//        webRequest.
        ApiResponse response =  ApiResponse.error().setData(ExceptionMessage.Sys.SYS_ERROR);
        return BeanUtil.beanToMap(response);
    }

    @Override
    public Throwable getError(WebRequest webRequest) {
        return super.getError(webRequest);
    }
}
