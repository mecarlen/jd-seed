package com.jd.seed.dictionary.city.web;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Api(description = "HTTP GET 方法")
@Controller
@RequestMapping(value = "/http")
public class HttpGetController {

	@GetMapping(value = "/get")
	public String get() {

		return "http/get";
	}

	@ApiOperation("纯URL请求")
	@GetMapping(value = "/get/url")
	public @ResponseBody BaseResult<String> url(
			@ApiParam("url") @RequestParam(value = "url", required = true) String url) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).get().build();
		Response response = client.newCall(request).execute();
		BaseResult<String> result = new BaseResult<String>();
		result.setCode(response.code());
		if (response.isSuccessful()) {
			result.setResult(response.body().string());
		} else {
			result.setMessage(response.message());
		}
		return result;
	}

	@PostMapping(value = "/get/url/paramsJson")
	public @ResponseBody BaseResult<String> urlAndJsonParams(String url, @RequestBody Map<String, Object> paramlist)
			throws IOException {
		OkHttpClient client = new OkHttpClient();
		StringBuilder params = new StringBuilder("?");
		paramlist.entrySet().forEach(node -> {
			params.append("&").append(node.getKey()).append("=").append(node.getValue());
		});
		url += params.toString();
		Request request = new Request.Builder().url(url).get().build();
		Response response = client.newCall(request).execute();
		BaseResult<String> result = new BaseResult<String>();
		result.setCode(response.code());
		if (response.isSuccessful()) {
			if (null != response.priorResponse()) {
				result.setCode(response.priorResponse().code());
				if (response.priorResponse().isSuccessful()) {
					result.setResult(response.priorResponse().body().toString());
				} else {
					result.setMessage("redirect " + response.priorResponse().request().url());
				}
			} else
				result.setResult(response.body().string());
		} else
			result.setMessage(response.message());
		return result;
	}

}
