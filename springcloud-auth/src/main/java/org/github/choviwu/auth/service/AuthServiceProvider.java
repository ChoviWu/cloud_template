package org.github..auth.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.github..api.auth.AuthService;
import org.github..auth.auth.UserAuthentication;
import org.github..auth.feign.MqConsumer;
import org.github..auth.mapper.BasUserTMapper;
import org.github..auth.mapper.SysResourceMapper;
import org.github..auth.mapper.SysRoleMapper;
import org.github..auth.mapper.SysRoleResourceMapper;
import org.github..common.commons.Pages;
import org.github..common.exception.ExceptionMessage;
import org.github..common.model.BasUser;
import org.github..common.model.BasUserT;
import org.github..common.model.SysResource;
import org.github..common.model.SysRole;
import org.github..common.model.dto.BasUserDTO;
import org.github..common.mq.RabbitMqTag;
import org.github..common.util.AssertUtil;
import org.github..common.util.JsonUtils;
import org.github..common.bean.UserDetails;
import org.github..common.bean.UserLogin;
import org.github..core.conf.TokenManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author
 * @date 2018/8/6
 * Description :
 */
@RestController
public class AuthServiceProvider extends ServiceImpl<BasUserTMapper, BasUserT>  implements AuthService {

    @Autowired
    SysRoleMapper basRoleMapper;
    @Autowired
    SysRoleResourceMapper roleResourceMapper;
    @Autowired
    BasUserTMapper userMapper;
    @Autowired
    SysResourceMapper resourceMapper;
    @Autowired
    UserAuthentication authentication;
    @Autowired
    TokenManage tokenManage;
    @Autowired
    MqConsumer mqSendMessage;

    @Override
    public List<SysResource> getListByRoleId(Integer roleId) {
        return resourceMapper.getListByRoleId(roleId);
    }

    @Override
    public SysRole getOneByUserId(Integer userId) {
        return basRoleMapper.getOneByUserId(userId);
    }

    @Override
    public BasUserT getOneByUserNameAndPassword(String userName, String password) {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("user_name",userName).eq("password",password);
        return this.selectOne(wrapper);
    }

    @Override
    public Pages<BasUserT>  getUserList(Pages page,EntityWrapper wrapper) {
         page.setRecords(this.selectList(wrapper));
         return page;
    }

    @Override
    public Object getOneByUserName(String userName) {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.like("user_name",userName);
        BasUserT user =  this.selectOne(wrapper);
        mqSendMessage.sendMessage(RabbitMqTag.EXCHANGE,RabbitMqTag.LOGIN_KEY+System.currentTimeMillis(), RabbitMqTag.LOGIN, JsonUtils.toJson(user));
        mqSendMessage.sendMessage(RabbitMqTag.EXCHANGE,RabbitMqTag.ARTICLE_SEND_KEY+System.currentTimeMillis(),RabbitMqTag.ARTICLE_SEND,JsonUtils.toJson(user));
        return user;
    }

    @Override
    public String  userLogin(String userName, String password) {
        AssertUtil.isNullOrEmpty(userName, ExceptionMessage.User.USER_NAME_IS_NOT_NULL);
        AssertUtil.isNullOrEmpty(password, ExceptionMessage.User.USER_PASSWORD_IS_NOT_NULL);
        UserLogin login = new UserLogin();
        login.setUserName(userName);
        login.setPassword(password);
        login.setUserType(1);
        UserDetails userDetails = authentication.login(login);
        String token = tokenManage.generatKey(userDetails);
        return token;
    }

    /**
     * 二维码登录模拟
     * @param uuid
     * @param token
     * @return
     */
    @Override
    public BasUserDTO scanQr(String uuid, String token) {
        String json = tokenManage.getUserByToken(token);
        UserDetails details = JsonUtils.json2Object(json,UserDetails.class);
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("user_name",details.getUserName());
        BasUserT user =  this.selectOne(wrapper);
        user.setPassword("");
        BasUserDTO dto = new BasUserDTO();
        dto.setToken(token);
        dto.setUserId(null);
        dto.setUserName(details.getUserName());
        return dto;
    }

    @Override
    public Object checkHeaderToken(String token) {

        return tokenManage.getUserByToken(token);
    }

    @Override
    public BasUserT getUserByKey(Integer userId) {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("id",userId);
        BasUserT user = this.selectOne(wrapper);
        AssertUtil.isNullOrEmpty(user,ExceptionMessage.User.USER_IS_NULL);
        return user;
    }
}
