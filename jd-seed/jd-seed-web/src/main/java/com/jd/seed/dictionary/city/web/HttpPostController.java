package com.jd.seed.dictionary.city.web;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * <pre>
 * http协议 GET请求
 * </pre>
 * 
 * @author mecarlen 2017年11月27日 下午3:13:46
 */
@Api(tags = "http请求POST方式", description = "http请求POST方式")
@RestController
@RequestMapping(value = "/http/post", produces = "application/json")
public class HttpPostController {

	@ApiOperation("通过一个URL请求")
	@RequestMapping(value = "/url", method = RequestMethod.POST)
	public String url(@ApiParam("请求URL") @RequestParam String url, @ApiParam("请求JSONObject参数") @RequestParam String content) throws IOException {
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), content);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = client.newCall(request).execute();
		if(response.isSuccessful())
			return response.body().toString();
		else
			return "fail:"+response.message();
	}
}
