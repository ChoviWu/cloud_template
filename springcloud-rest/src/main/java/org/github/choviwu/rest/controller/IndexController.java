package org.github..rest.controller;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.github..common.commons.Pages;
import org.github..common.commons.Paginator;
import org.github..common.model.BasUser;
import org.github..common.model.BasUserT;
import org.github..common.util.JsonUtils;
import org.github..core.annotation.Permission;
import org.github..common.bean.UserDTO;
import org.github..common.bean.UserDetails;
import org.github..core.annotation.SignMatcher;
import org.github..rest.service.AuthServiceConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @date 2018/5/30
 */
@RestController
@Slf4j
@Api(value = "首页")
public class IndexController {


    @Autowired
    private AuthServiceConsumer authServiceConsumer;
    @Autowired
    UserDetails userDetails;

    @ApiOperation(value = "404")
    @RequestMapping
    public Object error(HttpServletRequest request,HttpServletResponse response,@RequestHeader(value = "sign",required = false) String header){
        log.info("======================sign : "+header);
        log.info("======================status : "+response.getStatus());
        log.info("=====================parameters : " + JsonUtils.toJson(request.getParameterMap()));
        Map<String, Object> message = new HashMap<>();
        message.put("code", "404");
        message.put("msg", "请求失败");
        message.put("data", "Not Found");
        return message;
    }
    @ApiOperation(value = "登录授权",notes = "登录")
    @ApiModelProperty
    @SignMatcher(type = 0)
    @RequestMapping(value = "/auth",produces = { "application/json;charset=UTF-8" })
    public Object auth(String userName, String password) {
        Object obj =  authServiceConsumer.userLogin(userName, password);
        return obj;
    }

    @ApiOperation(value = "通过用户名查询用户信息",notes = "查询用户信息")
    @Permission
    @RequestMapping(value = "/user/getOneByUserName")
    public Object getOneByUserName(String userName) {
        return authServiceConsumer.getOneByUserName(userName);
    }


    @ApiOperation(value = "获取用户列表，分页",notes = "获取用户列表，分页")
    @RequestMapping(value = "/user/getList")
    public Object getList(Pages<BasUserT> page){
        EntityWrapper wrapper = new EntityWrapper();
        Map map = new HashMap();
        map.put("userName","");
        wrapper.like("user_name",(String )map.get("userName"), SqlLike.DEFAULT).eq("phone",map.get("phone"));
        Object obj =  authServiceConsumer.getUserList(page,wrapper);
        return obj;
    }
}
