package org.github..common.bean;

import lombok.Data;
import org.github..common.annotation.Serializer;

import java.io.Serializable;

/**
 * @author
 * @date 2018/8/23
 * Description :
 */
@Data
public class UserDTO implements Serializable {

    private String token;
    private UserDetails userDetail;

}
