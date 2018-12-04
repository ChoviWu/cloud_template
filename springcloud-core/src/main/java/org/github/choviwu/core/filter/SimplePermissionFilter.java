package org.github..core.filter;

import lombok.extern.slf4j.Slf4j;
import org.github..core.annotation.Permission;
import org.github..common.bean.UserDetails;
import org.github..common.annotation.ResourcesUrl;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @date 2018/7/13
 * Description :
 */
@Slf4j
public class SimplePermissionFilter extends AbstractFilter {

    @Override
    public void matchPermission(Permission permission, UserDetails userDetails) {
        HttpServletRequest request = userDetails.getRequest();
        ResourcesUrl resourcesUrl = permission.resourceUrl()[0];

    }
}
