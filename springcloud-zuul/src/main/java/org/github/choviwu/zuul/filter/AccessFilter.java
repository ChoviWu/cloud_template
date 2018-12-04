package org.github..zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.github..zuul.bean.FilterOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * @author
 * @date 2018/8/7
 * Description :  权限拦截  用户上游用户请求未进入微服务内
 */
public class AccessFilter extends ZuulFilter {

    @Autowired
    Environment environment;

    /**
     * 过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。这里定义为pre,代表会在请求被路由之前执行。
     * pre：可以在请求被路由之前调用
     * route：在路由请求时候被调用
     * post：在route和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行。
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterOrder.ACCESS_FILTER;
    }

    /**
     * 判断该过滤器是否需要被执行。这里我们直接返回了true,因此该过滤器对所有请求都会生效。实际运用中我们可以利用该函数来指定过滤器的有效范围
     * 可进行设置白名单
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，
     * 然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码，
     * 当然也可以进 一步优化我们的返回，比如，通过ctx.setResponseBody(body)对返回的body内容进行编辑等
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();
        Enumeration<String> enumeration = request.getHeaderNames();

        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                String value = request.getHeader(name);
                context.addZuulRequestHeader(name, value);
            }
        }
        Collection<String> responseE = response.getHeaderNames();
        if (!responseE.isEmpty()) {
            Iterator<String> iterator = responseE.iterator();
            while (iterator.hasNext()) {
                String name = iterator.next();
                String value = request.getHeader(name);
                context.addZuulRequestHeader(name, value);
            }
        }
        return null;
    }
}
