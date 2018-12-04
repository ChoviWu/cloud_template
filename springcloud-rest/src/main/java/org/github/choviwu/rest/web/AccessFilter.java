package org.github..rest.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.github..common.exception.ExceptionMessage;
import org.github..common.thread.RequestHolder;
import org.github..common.util.IpUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author
 * @date 2018/7/25
 * Description : accessToken checking
 */
@Slf4j
@WebFilter("accessFilter")
public class AccessFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, x-access-token,x-user-token,sign");
        IpUtils.clear();
        IpUtils.set(request);
        RequestHolder.setRequestLocal(request);
        //compare success redis key delete
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() { }

    private void error(HttpServletResponse response,ExceptionMessage exceptionMessage) throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setStatus(403);
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> msg = new HashMap<String, Object>();
        msg.put("data", exceptionMessage.getMsg());
        msg.put("code", exceptionMessage.getCode());
        msg.put("msg", "请求失败");
        String json = mapper.writeValueAsString(msg);
        out.print(json);
        out.flush();
        out.close();
    }

    public static boolean matchUrl(final String requestUrl, final Integer type) {
        List<String> list = type == 1 ? getNeedUrl() : getNotNeedUrl();
        for (String url : list) {
            if (requestUrl.contains(url)) {
                return true;
            }
        }
        return false;
    }

    //need url
    private static List<String> getNeedUrl() {
        String[] urls = new String[]{
                "/user/", "/sys/"
        };
        return Arrays.asList(urls);
    }


    //no need url
    private static List<String> getNotNeedUrl() {
        String[] urls = new String[]{
                "/enreka/", "/login/", "/doLogin/"
        };
        return Arrays.asList(urls);
    }

}
