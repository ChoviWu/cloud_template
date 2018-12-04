package org.github..core.encrypt;

import org.github..common.util.MD5Utils;
import org.github..common.bean.UserDetails;
import org.github..common.bean.UserTokenAuth;

import java.io.UnsupportedEncodingException;


/**
 * @author
 * @date 2018/7/28
 * Description :
 */
public abstract class PasswordMatcher implements CredentialsMatcher {

    protected UserDetails.AES aes;

    public PasswordMatcher(UserDetails.AES aes) {
        this.aes = aes;
    }

    public PasswordMatcher() {
        this.aes = aes;
    }

    protected static String toMD5(String password) {
        try {
            return MD5Utils.toMD5(password);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean passwordMatcher(UserTokenAuth tokenAuth, UserDetails userDetails) {
        this.aes = userDetails.getAes();
        if (this.aes == UserDetails.AES.MD5) {
            return new MD5Matcher().passwordMatcher(tokenAuth, userDetails);
        }
        return false;
    }
}
