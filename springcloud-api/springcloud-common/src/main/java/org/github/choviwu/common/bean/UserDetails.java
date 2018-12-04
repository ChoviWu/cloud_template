package org.github..common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.github..common.annotation.Serializer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author
 * @date 2018/7/11
 * Description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    /**
     * user permission
     */
    private List<UserPermission> userPermissions;
    /**
     * userName ...
     */
    private String userName;
    /**
     * current User request
     */
    private HttpServletRequest request;
    /**
     * user login object
     */
    private UserLogin userLogin;

    /**
     * password not encrypt
     */
    private String password;

    private AES aes = AES.MD5;


    public enum AES {
        MD5,
        RSA32,;

        AES() {

        }
    }

}
