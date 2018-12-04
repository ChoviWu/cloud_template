package org.github..common.annotation;

import java.lang.annotation.*;

/**
 * @author
 * @date 2018/7/10
 * Description :
 */

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Serializer {
}
