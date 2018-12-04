package org.github..rest.base;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {

    private String USER_ID_NAME="userId";

    private String VERIFY_CODE="verifyCode";

    private String SMS_CODE="smsCode";

    private String EMPTY="";



    public HttpServletRequest getRequest(){
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();
        return request;
    }

    public HttpServletResponse getResponse(){
        HttpServletResponse response = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }

    public HttpSession getSession(){
        return getRequest().getSession();
    }

    public ServletContext getServletContext(){
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        return  servletContext;
    }

    public Integer getSessionUserId(){
        Integer userId=0;
        Object obj=getSession().getAttribute(USER_ID_NAME);
        if(obj==null) return userId;
        if(obj instanceof  Integer){
            return (Integer)obj;
        }else{
            return userId;
        }
    }

    public String getSessionValidCode(){
        Object obj = getSession().getAttribute(VERIFY_CODE);
        if(obj==null) return EMPTY;
        if(obj instanceof  String){
            return (String)obj;
        }else{
            return EMPTY;
        }
    }

    public String getSessionSmsCode(){
        Object obj = getSession().getAttribute(SMS_CODE);
        if(obj==null) return EMPTY;
        if(obj instanceof  String){
            return (String)obj;
        }else{
            return EMPTY;
        }
    }


    public Integer getCurrentUser(){
        return Integer.parseInt(getSession().getAttribute("employeeId").toString());
    }

}
