package org.github..core.encrypt;

import org.github..common.util.AESCrypt;
import org.github..common.util.MD5Utils;

import java.io.UnsupportedEncodingException;

/**
 * @author
 * @date 2018/7/28
 * Description :
 */
public final class Encrypt {

    private static String key;

    public Encrypt(String key) {
        Encrypt.key = key;
    }

    public static String md5(String password) {
        try {
            return MD5Utils.toMD5(password);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String aes(String password) {
        String text = AESCrypt.AES_Encrypt(key, password);
        return text;
    }

}
