package org.github..mq.feign;

import org.github..api.auth.AuthService;
import org.github..common.constant.FeignConstants;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author
 * @date 2018/8/6
 * Description :
 */
@FeignClient(value = FeignConstants.AUTH_SERVICE)
public interface AuthServiceConsumer extends AuthService {
}
