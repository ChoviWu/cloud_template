package org.github..core.feign;

import com.xiaoleilu.hutool.io.IoUtil;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.github..common.exception.BusException;
import org.github..common.exception.ExceptionMessage;
import org.github..common.util.AssertUtil;
import org.github..common.util.JsonUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Map;

/**
 * @author
 * @date 2018/8/8
 * Description : feign调用失败
 */
public class MyErrorDecoder implements ErrorDecoder {


    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String responseBody;
            if (response == null || response.body() == null) {
                Integer status = response.status();
                //404
                AssertUtil.isTrue(HttpStatus.NOT_FOUND.value() != status, ExceptionMessage.Sys.SYS_NOT_FOUND_ERROR);
            }
            responseBody = IoUtil.read(response.body().asInputStream(), "UTF-8");
            Map<String, String> retMap = JsonUtils.json2Map(responseBody);
            String code = retMap.get("code");
            String message = retMap.get("message");
            String status = retMap.get("status");
            if (status != null) {
                throw new BusException(Integer.parseInt(status), message);
            } else {
                throw new BusException(Integer.parseInt(code), message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new BusException(ExceptionMessage.Sys.SYS_ERROR);
    }
}
