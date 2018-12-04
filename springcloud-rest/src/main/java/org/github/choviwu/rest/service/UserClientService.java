package org.github..rest.service;

import org.github..api.user.UserService;
import org.github..common.commons.PageList;
import org.github..common.commons.Paginator;
import org.github..common.constant.FeignConstants;
import org.github..common.model.BasUser;
import org.github..rest.service.hystricx.HystrixUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(value = FeignConstants.USER_SERVICE,fallback = HystrixUser.class)
public interface UserClientService {

//    @RequestMapping(value = "api/user/getList")
//    PageList getList(Paginator paginator);
//
//    @RequestMapping(value = "api/user/doRegister")
//    void doRegister(BasUser user);

}
