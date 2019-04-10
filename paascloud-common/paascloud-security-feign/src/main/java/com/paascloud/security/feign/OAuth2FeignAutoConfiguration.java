/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：OAuth2FeignAutoConfiguration.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.paascloud.security.feign;

import feign.Logger;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * The class O auth 2 feign auto configuration.
 *
 * @author paascloud.net @gmail.com
 */
@Configuration
@EnableConfigurationProperties(Oauth2ClientProperties.class)
public class OAuth2FeignAutoConfiguration {

	@Bean
	public Retryer feignRetryer() {
		return new Retryer.Default(600, SECONDS.toMillis(6000), 1);
	}


}