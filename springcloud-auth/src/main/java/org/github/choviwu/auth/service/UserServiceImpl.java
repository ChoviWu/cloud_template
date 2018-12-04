package org.github..auth.service;

import org.github..api.user.UserService;
import org.github..common.commons.PageList;
import org.github..common.commons.Paginator;
import org.github..common.model.BasUser;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @date 2018/8/13
 * Description :
 */
@RestController
public class UserServiceImpl implements UserService {

//    @Override
//    public PageList getList(Paginator paginator) {
//        return null;
//    }

    @Override
    public void doRegister(BasUser user) {

    }
}
