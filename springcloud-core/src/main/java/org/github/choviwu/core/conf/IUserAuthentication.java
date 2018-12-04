package org.github..core.conf;


import org.github..common.bean.UserDetails;
import org.github..common.bean.UserLogin;

/**
 * @author
 * @date 2018/7/11
 * Description : authentication interface
 * this interface's implements will di  bean
 */
public interface IUserAuthentication {

    UserDetails authenticating(UserLogin userLogin);

    UserDetails login(UserLogin userLogin);

    Object logout(Integer userId);
}
