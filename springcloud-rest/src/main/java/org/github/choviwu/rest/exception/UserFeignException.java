package org.github..rest.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import lombok.extern.slf4j.Slf4j;
import org.github..common.exception.BusException;
import org.github..common.exception.ExceptionMessage;
import org.github..common.util.JsonUtils;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * @date 2018/7/28
 * Description : feign Exception convert manager
 */
@Configuration
@Slf4j
public class UserFeignException implements ErrorDecoder {


    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            if (response.body() != null) {
                String body = Util.toString(response.body().asReader());
                log.error(body);
                ExceptionInfo exceptionInfo = JsonUtils.json2Object(body,ExceptionInfo.class);
                BusException busException = new BusException(exceptionInfo.getCode(),exceptionInfo.getData());
                return busException;

            }
        } catch (Exception var4) {
            log.error(var4.getMessage());
            return new BusException(500,var4.getMessage());
        }
        return new BusException(ExceptionMessage.Sys.SYS_ERROR);
    }
}
