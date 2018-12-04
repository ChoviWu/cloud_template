package org.github..rest.web;

import org.github..api.web.ApiResponse;
import org.github..common.util.JsonUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author
 * @date 2018/5/30
 * json 响应加密类
 */
@ControllerAdvice(basePackages = "org.github..rest.controller")
public class Response implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        System.out.println(converterType);
//        if(converterType.equals(MappingJackson2HttpMessageConverter.class)){
//            return true;
//        }
//        return false;
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> converterType, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(converterType.equals(MappingJackson2HttpMessageConverter.class)){
            if(o.toString().contains("失败")){
                return o;
            }
            ApiResponse response =  ApiResponse.success().setData(o);
            return response;
        }else{
            ApiResponse response =  ApiResponse.success().setData(o);
            return JsonUtils.toJson(response);
        }
    }
}
