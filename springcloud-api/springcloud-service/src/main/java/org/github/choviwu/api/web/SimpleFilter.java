package org.github..api.web;

import lombok.extern.slf4j.Slf4j;
import org.github..common.thread.RequestHolder;
import org.github..common.util.IpUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author
 * @date 2018/5/30
 */
@Slf4j
@WebFilter("simpleFilter")
public class SimpleFilter implements Filter {



    @Override
    public void init(FilterConfig filterConfig) {  }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        IpUtils.set(request);
        RequestHolder.setRequestLocal(request);
        filterChain.doFilter(request, response);
        log.info("Request IP is {}", IpUtils.get());
    }

    @Override
    public void destroy() {}
}
