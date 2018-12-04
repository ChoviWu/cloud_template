package org.github..auth.auth;

import org.github..auth.mapper.BasUserTMapper;
import org.github..common.bean.ResourceFactory;
import org.github..common.bean.UserDetails;
import org.github..common.bean.UserLogin;
import org.github..common.bean.UserPermission;
import org.github..core.conf.IUserAuthentication;
import org.github..core.encrypt.CredentialsMatcher;
import org.github..auth.mapper.SysResourceMapper;
import org.github..auth.mapper.SysRoleMapper;
import org.github..common.base.Message;
import org.github..common.constant.RedisConstants;
import org.github..common.exception.ExceptionMessage;
import org.github..common.model.BasUser;
import org.github..common.model.SysResource;
import org.github..common.model.SysRole;
import org.github..common.util.AssertUtil;
import org.github..common.util.JsonUtils;
import org.github..common.util.MD5Utils;
import org.github..redis.RedisRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author
 * @date 2018/7/11
 * Description : 赋权
 */
@Component
public class UserAuthentication implements IUserAuthentication {

    private final UserDetails userDetails;
    private final ResourceFactory resourceFactory;
    private final RedisRepositoryUser redisRepository;
    private final SysResourceMapper resourceMapper;
    private final SysRoleMapper roleMapper;
    private final CredentialsMatcher credentialsMatcher;
    private final BasUserTMapper userMapper;

    @Autowired
    UserAuthentication(UserDetails userDetails,
                       ResourceFactory resourceFactory,
                       RedisRepositoryUser redisRepository,
                       CredentialsMatcher credentialsMatcher,
                       SysResourceMapper resourceMapper,
                       SysRoleMapper roleMapper,
                       BasUserTMapper userMapper) {
        this.userDetails = userDetails;
        this.resourceFactory = resourceFactory;
        this.redisRepository = redisRepository;
        this.credentialsMatcher = credentialsMatcher;
        this.resourceMapper = resourceMapper;
        this.roleMapper = roleMapper;
        this.userMapper = userMapper;
    }

    /**
     * user authentication
     *
     * @return
     */
    @Override
    public UserDetails authenticating(UserLogin userLogin) {
        SysRole role = roleMapper.getOneByUserId(userLogin.getUserId());
        AssertUtil.isNullOrEmpty(role, ExceptionMessage.User.USER_ROLE_IS_NULL);
        List<SysResource> list = resourceMapper.getListByRoleId(role.getId());
        List<UserPermission> userPermissions = new ArrayList<>();
        for (SysResource resource : list) {
            UserPermission userPermission = new UserPermission();
            userPermission.setResourceUrl(resource.getResourceUrl());
            userPermission.setCode(UUID.randomUUID().toString().replace("-", ""));
            userPermission.setIsMenu(false);
            userPermission.setPermissionName(resource.getResourceName());
            userPermissions.add(userPermission);
        }
        userLogin.setLoginTime(System.currentTimeMillis());
        //7 days alter
        userLogin.setExpiredTime(60 * 60 * 24 * 7L);
        userDetails.setUserLogin(userLogin);
        userDetails.setUserPermissions(userPermissions);
        userDetails.setUserName(userLogin.getUserName());
        //matcher password
        credentialsMatcher.passwordMatcher(userLogin, userDetails);
        resourceFactory.createResource(userPermissions);
        return userDetails;
    }

    /**
     * login auth 登录授权
     *
     * @param userLogin
     * @return
     */
    @Override
    public UserDetails login(UserLogin userLogin) {
        //login check
        BasUser user = redisRepository.get(RedisConstants.USER_INFO + userLogin.getUserName(), BasUser.class);
        String password;
        if (user != null) {
            password = getPassword(userLogin.getPassword(), user.getSalt());
            AssertUtil.isTrue(user.getPassword().equals(password), ExceptionMessage.User.USER_PASSWORD_ERROR);
        } else {
            user = userMapper.getOneByUserName(userLogin.getUserName());
            AssertUtil.isNullOrEmpty(user, ExceptionMessage.User.USER_NAME_ERROR);
            password = getPassword(userLogin.getPassword(), user.getSalt());
            user = userMapper.getOneByUserNameAndPassword(userLogin.getUserName(), password);
            AssertUtil.isNullOrEmpty(user, ExceptionMessage.User.USER_PASSWORD_ERROR);
            //put user password and userInfo to redis
            redisRepository.set(RedisConstants.USER_INFO + userLogin.getUserId(), JsonUtils.toJson(user));
            //from db
        }
        userDetails.setPassword(userLogin.getPassword() + user.getSalt());
        userLogin.setPassword(password);
        userLogin.setUserId(user.getId());
        return this.authenticating(userLogin);
    }

    /**
     * 退出登录
     *
     * @param userId
     * @return
     */
    @Override
    public Object logout(Integer userId) {
        userDetails.setUserPermissions(null);
        resourceFactory.removeAll();
        redisRepository.del(RedisConstants.USER_LOGIN + userId);
        redisRepository.del(RedisConstants.USER_TOKEN + userId);
        return Message.OK;
    }

    private String getPassword(String password, String salt) {
        try {
            password = MD5Utils.toMD5(password + salt);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return password;
    }

}
