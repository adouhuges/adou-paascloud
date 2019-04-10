/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：AuthHeaderFilter.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

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
 * The class Auth header filter.
 *
 * @author paascloud.net @gmail.com
 */
@Slf4j
@Component
//请求过滤,通过api网关的过滤器先将所有的请求拦截起来,在跟据情况进行处理
/**
1.filterType方法的返回值为过滤器的类型，过滤器的类型决定了过滤器在哪个生命周期执行，pre表示在路由之前执行过滤器，其他可选值还有post、error、route和static，当然也可以自定义。
2.filterOrder方法表示过滤器的执行顺序，当过滤器很多时，这个方法会有意义。
3.shouldFilter方法用来判断过滤器是否执行，true表示执行，false表示不执行，在实际开发中，我们可以根据当前请求地址来决定要不要对该地址进行过滤，这里我直接返回true。
4.run方法则表示过滤的具体逻辑，假设请求地址中携带了login参数的话，则认为是合法请求，否则就是非法请求，如果是非法请求的话，首先设置ctx.setSendZuulResponse(false);表示不对该请求进行路由，然后设置响应码和响应值。这个run方法的返回值在当前版本(Dalston.SR3)中暂时没有任何意义，可以返回任意值。
 */
public class AuthHeaderFilter extends ZuulFilter {

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
		return "pre";
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
		return true;
	}

	/**
	 * Run object.
	 *
	 * @return the object
	 */
	@Override
	public Object run() {
		log.info("AuthHeaderFilter - 开始鉴权...");
		System.out.println("WUXINGHUDAYEHAI AuthHeaderFilter - 开始鉴权...");
		System.out.println("WUXINGHUDAYEHAI AuthHeaderFilter - 开始鉴权...");
		System.out.println("WUXINGHUDAYEHAI AuthHeaderFilter - 开始鉴权...");
		RequestContext requestContext = RequestContext.getCurrentContext(); //过滤器之间  通过该对象进行传输数据
		try {
			doSomething(requestContext);
		} catch (Exception e) {
			log.error("AuthHeaderFilter - [FAIL] EXCEPTION={}", e.getMessage(), e);
			throw new BusinessException(ErrorCodeEnum.UAC10011041);
		}
		return null;
	}

	private void doSomething(RequestContext requestContext) throws ZuulException {
		HttpServletRequest request = requestContext.getRequest();
		String requestURI = request.getRequestURI();
		System.out.print(request.getMethod());
		//请求方式等于OPTIONS 或者包含/auth 等等
		if (OPTIONS.equalsIgnoreCase(request.getMethod()) || requestURI.contains(AUTH_PATH) || requestURI.contains(LOGOUT_URI) || !requestURI.contains(ALIPAY_CALL_URI)) {
//		if (OPTIONS.equalsIgnoreCase(request.getMethod()) || requestURI.contains(AUTH_PATH) || requestURI.contains(LOGOUT_URI) || requestURI.contains(ALIPAY_CALL_URI)) {
			return;
		}
		String authHeader = RequestUtil.getAuthHeader(request);

		if (PublicUtil.isEmpty(authHeader)) {
			throw new ZuulException("刷新页面重试", 403, "check token fail");
		}

		if (authHeader.startsWith(BEARER_TOKEN_TYPE)) {
			requestContext.addZuulRequestHeader(HttpHeaders.AUTHORIZATION, authHeader);

			log.info("authHeader={} ", authHeader);
			// 传递给后续微服务
			requestContext.addZuulRequestHeader(CoreHeaderInterceptor.HEADER_LABEL, authHeader);
		}
	}

}
