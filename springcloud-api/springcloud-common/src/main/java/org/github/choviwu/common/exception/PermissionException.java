package org.github..common.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author
 * @date 2018/7/11
 * Description :
 */
@Data
public final class PermissionException extends RuntimeException{

    private String message;
    private Integer code;

    public PermissionException(ExceptionMessage message) {
        super(message.getMsg());
        this.message = message.getMsg();
        this.code = message.getCode();
    }
}
