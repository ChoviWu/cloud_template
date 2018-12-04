package org.github..zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.github..common.util.JsonUtils;
import org.github..zuul.bean.FilterOrder;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @date 2018/8/17
 * Description :
 */
public class SignFilter extends ZuulFilter {

//    @Autowired
//    RedisRepository redisRepository;
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return FilterOrder.SIGN_FILTER;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String userToken = request.getHeader("access-x-user");
        String sign = request.getHeader("sign");
        //签名校验
        if(sign==null){
            Map map = new HashMap();
            map.clear();
            map.put("data","");
            map.put("code", 401);
            map.put("msg","session.timeout");
            //返回内容
            context.setResponseStatusCode(403);
            context.setSendZuulResponse(false);
            context.setResponseBody(JsonUtils.toJson(map));
        }else{

        }

        return null;
    }
}
