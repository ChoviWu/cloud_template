package org.github..core.encrypt;

import org.github..common.exception.ExceptionMessage;
import org.github..common.util.AssertUtil;
import org.github..common.bean.UserDetails;
import org.github..common.bean.UserTokenAuth;

/**
 * @author
 * @date 2018/7/28
 * Description :
 */
public class MD5Matcher extends PasswordMatcher {

    public MD5Matcher() {
    }

    @Override
    public boolean passwordMatcher(UserTokenAuth tokenAuth, UserDetails userDetails) {
        AssertUtil.isTrue(matcher(tokenAuth, userDetails), ExceptionMessage.User.USER_PASSWORD_ERROR);
        return true;
    }

    boolean matcher(UserTokenAuth tokenAuth, UserDetails userDetails) {
        String password = tokenAuth.getCredentials();
        String toMd5 = toMD5(userDetails.getPassword());
        if (password.equals(toMd5)) {
            return true;
        }
        return false;
    }

}
