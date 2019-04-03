/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：MallAuthRestController.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.paascloud.provider.web.mall;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.paascloud.core.support.BaseController;
import com.paascloud.provider.model.dto.ProductCategoryDto;
import com.paascloud.provider.model.dto.ProductReqDto;
import com.paascloud.provider.model.vo.ProductDetailVo;
import com.paascloud.provider.service.MdcProductCategoryQueryFeignApi;
import com.paascloud.provider.service.MdcProductQueryFeignApi;
import com.paascloud.security.feign.Oauth2ClientProperties;
import com.paascloud.wrapper.WrapMapper;
import com.paascloud.wrapper.Wrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * The class Mall auth rest controller.
 *
 * @author paascloud.net @gmail.com
 */
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - MallAuthRestController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MallAuthRestController extends BaseController {

	@Resource
	private MdcProductQueryFeignApi mdcProductQueryFeignApi;

	@Resource
	private MdcProductCategoryQueryFeignApi mdcProductCategoryQueryFeignApi;

	/**
	 * 查询商品列表.
	 *
	 * @param productReqDto the product req dto
	 *
	 * @return the product category dto by pid
	 */
	@PostMapping(value = "/product/queryProductList")
	@ApiOperation(httpMethod = "POST", value = "查询商品列表")
	public Wrapper<PageInfo> queryProductList(@RequestBody ProductReqDto productReqDto) {
		logger.info("getProductCategoryDtoByPid - 查询分类信息 productReqDto={}", productReqDto);
		return mdcProductCategoryQueryFeignApi.getProductList(productReqDto);
	}
	
	@Autowired
	Oauth2ClientProperties proper;
	/**
	 * 查询商品详情信息.
	 *
	 * @param productId the product id
	 *
	 * @return the wrapper
	 */
	@GetMapping(value = "/product/queryProductDetail/{productId}")
	@ApiOperation(httpMethod = "POST", value = "查询商品详情信息")
	public Wrapper<ProductDetailVo> queryProductDetail(@PathVariable Long productId) {
		logger.info("getProductCategoryDtoByPid - 查询商品详情信息 productId={}", productId);
		System.out.println(proper.getAccessTokenUrl());
		Object object=mdcProductQueryFeignApi.getProductDetail(productId);
		System.out.println(object);
		return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, new ProductDetailVo());
//		return mdcProductQueryFeignApi.getProductDetail(productId);
	}

	/**
	 * 查询分类信息.
	 *
	 * @param pid the pid
	 *
	 * @return the product category dto by pid
	 */
	@GetMapping(value = "/category/getProductCategoryDtoByPid/{pid}")
	public Wrapper<List<ProductCategoryDto>> getProductCategoryDtoByPid(@PathVariable Long pid) {
		logger.info("getProductCategoryDtoByPid - 查询分类信息 pid={}", pid);
		Wrapper<List<ProductCategoryDto>> productCategoryData = mdcProductCategoryQueryFeignApi.getProductCategoryData(pid);
		logger.info("productCategoryData={}", productCategoryData);
		return productCategoryData;
	}
}
