package org.github..rest.exception;

import lombok.*;
import org.github..common.annotation.Serializer;

/**
 * @author
 * @date 2018/7/28
 * Description : print error info
 */
@Setter
@Getter
@EqualsAndHashCode
@Serializer
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionInfo {

    @NonNull
    private String msg;
    @NonNull
    private String data;
    @NonNull
    private Integer code ;

}
