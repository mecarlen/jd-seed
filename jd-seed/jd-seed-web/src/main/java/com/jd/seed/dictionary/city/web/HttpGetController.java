package com.jd.seed.dictionary.city.web;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <pre>
 * http协议 GET请求
 * 
 * </pre>
 * 
 * @author mecarlen 2017年11月27日 上午10:07:03
 */
@Api(tags = "http请求GET方式", description = "http请求GET方式")
@RestController
@RequestMapping(value = "/http/get", produces = "application/json")
public class HttpGetController {

	@ApiOperation("通过一个URL请求")
	@RequestMapping(value = "/url", method = RequestMethod.GET)
	public String url(
			@ApiParam(value = "带参的URL", required = true) @RequestParam(value = "url", required = true) String url)
			throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).get().build();
		Response response = client.newCall(request).execute();
		if (response.isSuccessful())
			return response.body().string();
		else
			return "fail:" + response.body().string();
	}

	@ApiOperation("通过一个URL和参数JSON串请求")
	@RequestMapping(value = "/url/paramsJson", method = RequestMethod.POST)
	public String urlAndJsonParams(@ApiParam("请求URL")String url, @ApiParam("请求参数JSON串") @RequestBody Map<String,Object> paramlist) throws IOException {
		OkHttpClient client = new OkHttpClient();
		StringBuilder params = new StringBuilder("?");
		paramlist.entrySet().forEach(node -> {
			params.append("&").append(node.getKey()).append("=").append(node.getValue());
		});
		url += params.toString();
		Request request = new Request.Builder().url(url).get().build();
		Response response = client.newCall(request).execute();
		if (response.isSuccessful())
			return response.body().string();
		else
			return "fail:" + response.message();
	}

}
