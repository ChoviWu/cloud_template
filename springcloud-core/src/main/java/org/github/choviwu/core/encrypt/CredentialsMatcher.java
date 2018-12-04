package org.github..core.encrypt;

import org.github..common.annotation.Serializer;
import org.github..common.bean.UserDetails;
import org.github..common.bean.UserTokenAuth;

/**
 * @author
 * @date 2018/7/28
 * Description :
 */
@Serializer
public interface CredentialsMatcher {


    boolean passwordMatcher(UserTokenAuth tokenAuth, UserDetails userDetails);
}
