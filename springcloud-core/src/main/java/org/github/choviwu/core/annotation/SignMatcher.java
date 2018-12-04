package org.github..core.annotation;

import java.lang.annotation.*;

/**
 * @author
 * @date 2018/8/28
 * Description :
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface SignMatcher {

    /**
     * sign 检验类型
     * @return
     */
    int type() default 0;


}
