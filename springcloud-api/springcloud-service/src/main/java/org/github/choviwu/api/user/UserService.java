package org.github..api.user;

import org.apache.ibatis.annotations.Param;
import org.github..api.BaseService;
import org.github..common.commons.PageList;
import org.github..common.commons.Paginator;
import org.github..common.model.BasUser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping(value = "api/user")
public interface UserService  {

//    @RequestMapping(value = "getList")
//    PageList getList(Paginator paginator);

    @RequestMapping(value = "doRegister")
    void doRegister(BasUser user);

}