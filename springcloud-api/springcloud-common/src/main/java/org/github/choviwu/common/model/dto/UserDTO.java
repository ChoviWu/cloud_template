package org.github..common.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author
 * @date 2018/9/13
 * Description :
 */
@Data
@EqualsAndHashCode
@ToString
public class UserDTO implements Serializable {

    private Integer userId;
    private String userName;
    private String token;

}
