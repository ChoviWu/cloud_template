package org.github..common.bean;

import lombok.*;
import org.github..common.annotation.Serializer;

import java.lang.reflect.Method;

/**
 * @author
 * @date 2018/7/10
 * Description : User Role Permission
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPermission {

    /**
     * permissionName
     */
    private String permissionName;
    /**
     * resourceCode
     */
    private String code;
    /**
     * menu default false
     */
    private Boolean isMenu = false;
    /**
     * resource Url authorized
     */
    private String resourceUrl;

    private Method method;


}
