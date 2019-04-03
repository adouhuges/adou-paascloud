/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：PaasCloudDiscoveryApplication.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.paascloud.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * The class Paas cloud discovery application.
 *
 * @author paascloud.net@gmail.com
 */
//微服务配置中心(统一的配置中心),统一的配置中心也是一个微服务
//是一个Eureka客户端,是Config的Server端
//@EnableDiscoveryClient: 服务发现客户端注解，用于被发现。
//@EnableConfigServer: 开启配置中心服务器配置。
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
//@EnableEurekaClient
public class PaasCloudDiscoveryApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PaasCloudDiscoveryApplication.class, args);
	}
}
