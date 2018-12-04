package org.github..common.util;

import org.github..common.exception.BusException;
import org.github..common.exception.ExceptionMessage;
import org.springframework.util.StringUtils;

/**
 * @author
 * @date 2018/6/20
 * Description : judge utils
 */
public class AssertUtil {

    public static void isTrue(Boolean obj, ExceptionMessage message) {
        if(!obj){
            throw new BusException(message);
        }
    }

    public static void isFalse(Boolean obj, ExceptionMessage message) {
        if(obj){
            throw new BusException(message);
        }
    }

    public static void isNullOrEmpty(Object obj, ExceptionMessage message) {
        if(StringUtils.isEmpty(obj)){
            throw new BusException(message);
        }
    }

    public static void isNullOrEmpty(Object obj) {
        if(StringUtils.isEmpty(obj)){
            throw new BusException(ExceptionMessage.Sys.SYS_PARAM_ERROR);
        }
    }
}
