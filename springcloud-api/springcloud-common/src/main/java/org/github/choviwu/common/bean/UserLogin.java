package org.github..common.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.github..common.annotation.Serializer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @date 2018/7/11
 * Description :
 */
@Data
@NoArgsConstructor
public class UserLogin implements UserTokenAuth{

    /**
     * userName
     */
    private String userName;
    /**
     * user password encrypt if salt then add salt  else not
     */
    private String password;
    /**
     * expired Time
     */
    private Long expiredTime;
    /**
     * record login time
     */
    private Long loginTime;
    /**
     * this request info
     */
    private HttpServletRequest request;
    /**
     * add salt
     */
    private boolean isSalt = true;

    /**
     * user type
     */
    private Integer userType;
    /**
     * user Id
     */
    private Integer userId;

    private String credentials;

    @Override
    public String getCredentials() {
        return getPassword();
    }
}
