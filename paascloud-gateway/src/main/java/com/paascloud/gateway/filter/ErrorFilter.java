package com.paascloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.paascloud.PublicUtil;
import com.paascloud.base.enums.ErrorCodeEnum;
import com.paascloud.base.exception.BusinessException;
import com.paascloud.core.interceptor.CoreHeaderInterceptor;
import com.paascloud.core.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author adouhu
 * @description error handler filter
 * @date 2019/4/10
 */
@Slf4j
@Component
public class ErrorFilter extends ZuulFilter {

    private static final String BEARER_TOKEN_TYPE = "Bearer ";
    private static final String OPTIONS = "OPTIONS";
    private static final String AUTH_PATH = "/auth";
    private static final String LOGOUT_URI = "/oauth/token";
    private static final String ALIPAY_CALL_URI = "/pay/alipayCallback";


    /**
     * Filter type string.
     中所描述的一样，Zuul 提供了四种过滤器的 API，分别为前置（Pre）、后置（Post）、路由（Route）和错误（Error）四种处理方式。
     一个请求会先按顺序通过所有的前置过滤器，之后在路由过滤器中转发给后端应用，得到响应后又会通过所有的后置过滤器，最后响应给客户端。在整个流程中如果发生了异常则会跳转到错误过滤器中。
     * @return the string
     */
    @Override
    public String filterType() {
        return "error";
    }

    /**
     * Filter order int.
     * number more small,priority high
     * @return the int
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * Should filter boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Boolean flag=(Boolean)requestContext.get("flag");
        if(flag) {
            return true;
        }
        return false;
    }

    /**
     * Run object.
     *
     * @return the object
     */
    @Override
    public Object run() {
        return null;
    }
}

