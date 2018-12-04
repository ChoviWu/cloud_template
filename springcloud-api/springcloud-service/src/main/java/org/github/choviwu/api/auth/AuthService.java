package org.github..api.auth;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import org.github..common.bean.UserDTO;
import org.github..common.commons.PageList;
import org.github..common.commons.Pages;
import org.github..common.commons.Paginator;
import org.github..common.model.BasUser;
import org.github..common.model.BasUserT;
import org.github..common.model.SysResource;
import org.github..common.model.SysRole;
import org.github..common.model.dto.BasUserDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @date 2018/8/6
 * Description :
 */
@RequestMapping("api")
public interface AuthService {


    @RequestMapping("resource/getList")
    List<SysResource> getListByRoleId(Integer roleId);


    @RequestMapping("role/getOneByUserId")
    SysRole getOneByUserId(Integer userId);

    /**
     * check user login status
     *
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "user/getOneByUserNameAndPassword")
    BasUserT getOneByUserNameAndPassword(@RequestParam("userName") String userName, @RequestParam("password") String password);

    @RequestMapping(value = "user/getList")
    Pages<BasUserT>  getUserList(@RequestParam("page") Pages<BasUserT> page,@RequestParam("wrapper") EntityWrapper wrapper);
    /**
     * get user
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "user/getOneByUserName")
    Object getOneByUserName(@RequestParam("userName") String userName);

    @RequestMapping(value = "user/login")
    public String userLogin(@RequestParam("userName") String userName, @RequestParam("password") String password);

    @RequestMapping(value = "user/scanQr")
    BasUserDTO scanQr(@RequestParam("uuid")String uuid, @RequestParam("token")String token);

    @RequestMapping(value = "user/checkHeaderToken")
    Object checkHeaderToken(@RequestParam("token") String token);

    @RequestMapping(value = "user/getUserByKey")
    BasUserT getUserByKey(@RequestParam("userId") Integer userId);

}
