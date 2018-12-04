package org.github..api.web;

import com.sun.beans.editors.StringEditor;
import org.github..common.exception.BusException;
import org.github..common.exception.PermissionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @date 2018/5/30
 * 全局异常处理器
 */
@ControllerAdvice(basePackages = {"org.github."})
public class ExceptionApi {


    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     *
     * @param binder the binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new StringEditor());
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Object exception(Exception ex) {
        ex.printStackTrace();
        ApiResponse response = ApiResponse.error();
        response.setData(ex.getMessage());
        response.setCode(-1);
        return response;
    }
    @ResponseBody
    @ExceptionHandler(BusException.class)
    @ResponseStatus(HttpStatus.OK)
    public Object busException(BusException ex){
        ex.printStackTrace();
        ApiResponse response = ApiResponse.error();
        response.setData(ex.getMessage());
        response.setCode(ex.getCode());
        return response;
    }

    @ResponseBody
    @ExceptionHandler(PermissionException.class)
    @ResponseStatus(HttpStatus.OK)
    public Object permissionException(PermissionException ex){
        ex.printStackTrace();
        ApiResponse response = ApiResponse.error();
        response.setData(ex.getMessage());
        response.setCode(ex.getCode());
        return response;
    }

}
