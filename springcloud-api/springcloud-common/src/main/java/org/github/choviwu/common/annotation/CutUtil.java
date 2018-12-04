package org.github..common.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author
 * @date 2018/7/9
 * Description :
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface CutUtil {

    @AliasFor(value = "name")
    String value() default "";


    String descprion() default "";
}
