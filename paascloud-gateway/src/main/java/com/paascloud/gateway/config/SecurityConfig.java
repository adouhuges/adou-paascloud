/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：SecurityConfig.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.paascloud.gateway.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * The class Security config.
 *
 * @author paascloud.net @gmail.com
 */
@Configuration
/**
 *   实现基于OAuth2的单点登录
	 WebSecurityConfigurerAdapter} provided by the user and annotated with
 * {@code @EnableOAuth2Sso}, it is enhanced by adding an authentication filter and an
 * authentication entry point. 
 * 那么将会添加身份验证过滤器和身份验证入口。
 */
@EnableOAuth2Sso 
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Configure.
	 *
	 * @param http the http
	 *
	 * @throws Exception the exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//CSRF是指跨站请求伪造（Cross-site request forgery）
		//关闭csrf保护
		http.csrf().disable();


	}


}
