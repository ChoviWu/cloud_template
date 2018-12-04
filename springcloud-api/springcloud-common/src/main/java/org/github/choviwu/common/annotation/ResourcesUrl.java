package org.github..common.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author
 * @date 2018/7/11
 * Description :
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ResourcesUrl {

    @AliasFor(value = "name")
    String value() default "";

    TYPE roleName() default TYPE.USER;

    String descrip() default "";

    enum TYPE{
        USER,
        ADMIN
    }

}
