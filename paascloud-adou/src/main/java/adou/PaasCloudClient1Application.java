/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：PaasCloudMdcApplication.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package adou;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The class Paas cloud mdc application.
 *
 * @author paascloud.net@gmail.com
 */

@EnableDiscoveryClient
@SpringBootApplication
public class PaasCloudClient1Application {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PaasCloudClient1Application.class, args);
	}
}

@RestController
@RequestMapping(value = "/adou")
class ServiceInstanceRestController {
	@Value("${spring.profiles.active}")
	private String active;

    @RequestMapping("/hello1")
    public String serviceInstancesByApplicationName() {
        return active;
    }
    @RequestMapping("/hello2")
    public String serviceInstancesByApplicationName2() {
    	return "hello2";
    }
}
