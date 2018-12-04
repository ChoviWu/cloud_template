package org.github..core.filter;

import lombok.extern.slf4j.Slf4j;
import org.github..common.annotation.ResourcesUrl;
import org.github..common.base.Constants;
import org.github..common.exception.ExceptionMessage;
import org.github..common.exception.PermissionException;
import org.github..core.annotation.Permission;
import org.github..common.bean.UserDetails;
import org.github..common.bean.UserPermission;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author
 * @date 2018/7/10
 * Description : PermissionFilter Default Checking permission
 */
@Slf4j
public class DefaultPermissionFilter extends AbstractFilter {


    @Override
    public void matchPermission(Permission permission, UserDetails userDetails) {
        HttpServletRequest request = userDetails.getRequest();
        ResourcesUrl [] resourcesUrl = permission.resourceUrl();


        if (Constants.ONE.equals(userDetails.getUserLogin().getUserType())) {
            //用户

        } else if (Constants.TWO.equals(userDetails.getUserLogin().getUserType())) {
            //管理员
        }
        boolean flag = this.matchUrl(userDetails);
        if (!flag) {
            //from db
            throw new PermissionException(ExceptionMessage.Permission.PERMISSION_NOT_ENOUGH);
        }
    }

    /**
     * this url have to authorized operation
     *
     * @param userDetails
     */
    private boolean matchUrl(UserDetails userDetails) {
        List<UserPermission> list = userDetails.getUserPermissions();
        HttpServletRequest request = userDetails.getRequest();
        String path = request.getServletPath();
        for (UserPermission user : list) {
            if (path.contains(user.getResourceUrl())) {

                return true;
            }
        }
        return false;
    }
}
