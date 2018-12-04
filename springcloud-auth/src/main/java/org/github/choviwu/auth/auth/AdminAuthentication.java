package org.github..auth.auth;

import org.github..common.bean.ResourceFactory;
import org.github..common.bean.UserDetails;
import org.github..common.bean.UserLogin;
import org.github..core.conf.IUserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2018/7/11
 * Description :
 */
@Component
public class AdminAuthentication implements IUserAuthentication {

    private final UserDetails userDetails;
    private final ResourceFactory resourceFactory;

    @Autowired
    AdminAuthentication(UserDetails userDetails, ResourceFactory resourceFactory) {
        this.userDetails = userDetails;
        this.resourceFactory = resourceFactory;
    }


    @Override
    public UserDetails authenticating(UserLogin userLogin) {
        userDetails.setUserLogin(userLogin);
        userDetails.setUserName(userLogin.getUserName());
        userDetails.setUserPermissions(resourceFactory.getList());

        resourceFactory.createResource(resourceFactory.getList());
        return userDetails;
    }

    @Override
    public UserDetails login(UserLogin userLogin) {
        return null;
    }

    @Override
    public Object logout(Integer userId) {
        return null;
    }
}
