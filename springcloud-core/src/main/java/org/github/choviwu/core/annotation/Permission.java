package org.github..core.annotation;


import org.github..core.filter.DefaultPermissionFilter;
import org.github..common.annotation.ResourcesUrl;

import java.lang.annotation.*;

/**
 * @author
 * @date 2018/7/10
 * Description : Permission Resource Class
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Permission {

    Class<?> clazz() default DefaultPermissionFilter.class;

    ResourcesUrl[] resourceUrl() default {};

}
