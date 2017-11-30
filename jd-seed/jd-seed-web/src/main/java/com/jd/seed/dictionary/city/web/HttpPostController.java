package com.jd.seed.dictionary.city.web;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <pre>
 * http协议 GET请求
 * </pre>
 * 
 * @author mecarlen 2017年11月27日 下午3:13:46
 */
@Api(description = "HTTP POST 方法")
@Controller
@RequestMapping(value = "/http")
public class HttpPostController {
	@ApiIgnore
	@GetMapping(value = "/post")
	public String post() {

		return "http/post";
	}

	@PostMapping(value = "/post/url")
	public @ResponseBody BaseResult<String> url(@RequestParam String url, @RequestParam String content)
			throws IOException {
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), content);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = client.newCall(request).execute();
		BaseResult<String> result = new BaseResult<String>();
		result.setCode(response.code());
		if (response.isSuccessful()) {
			if (null != response.priorResponse()) {
				result.setCode(response.priorResponse().code());
				if (response.priorResponse().isSuccessful())
					result.setResult(response.priorResponse().body().toString());
				else
					result.setResult("redirect url=" + response.priorResponse().request().url());
			} else {
				result.setResult(response.body().toString());
			}
		} else {
			result.setResult(url + " " + response.message());
		}
		return result;
	}
}
