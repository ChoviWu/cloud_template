package org.github..core.filter;


import org.github..core.annotation.Permission;
import org.github..common.bean.UserDetails;

/**
 * @author
 * @date 2018/7/10
 * Description :
 */
public abstract class AbstractFilter implements PermissionFilter {


    @Override
    public abstract void matchPermission(Permission permission, UserDetails userDetails);

}
