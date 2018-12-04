package org.github..common.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.github..common.annotation.Serializer;

import java.util.HashMap;

/**
 * @author
 * @date 2018/6/20
 * Description :
 */
@Serializer
@Data
@ToString
@EqualsAndHashCode
public class BaseResult<T> {


    private T data;
    private int code;
    private String msg;

    public BaseResult(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }


    public BaseResult(T data, int code) {
        this.data = data;
        this.code = code;
        this.msg  = "请求成功";
    }

    public BaseResult(T data) {
        this.data = data;
    }

    public BaseResult<T> success(T t, int code){
         return new BaseResult<T>(t,code);
    }
    public Object success(){
        return success(null,0000);
    }

    public Object success(T t){
        return success(t,0000);
    }


}
