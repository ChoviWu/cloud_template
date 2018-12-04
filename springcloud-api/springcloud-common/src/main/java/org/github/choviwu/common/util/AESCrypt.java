package org.github..common.util;

import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class AESCrypt {

//	private final static String keyStr = "HTTPSIFCERTORGCN";

	private static final String AESTYPE = "AES/ECB/PKCS5Padding";

	public static String AES_Encrypt(String keyStr,String plainText) {
		byte[] encrypt = null;
		try {
			Key key = generateKey(keyStr);
			Cipher cipher = Cipher.getInstance(AESTYPE);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encrypt = cipher.doFinal(plainText.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(Base64Coder.encode(encrypt));
	}

	public static String AES_Decrypt(String keyStr, String encryptData) {
		byte[] decrypt = null;
		try {
			Key key = generateKey(keyStr);
			Cipher cipher = Cipher.getInstance(AESTYPE);
			cipher.init(Cipher.DECRYPT_MODE, key);
			decrypt = cipher.doFinal(Base64Coder.decode(encryptData));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(decrypt).trim();
	}

	private static Key generateKey(String key) throws Exception {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
			return keySpec;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public static void main(String[] args) {

		String plainText = "371102198706271234";

		String encText = AES_Encrypt("HTTPSIFCERTORGCN",plainText);
		String decString = AES_Decrypt("HTTPSIFCERTORGCN", encText);

		System.out.println(encText);
		System.out.println(decString);

	}

}
