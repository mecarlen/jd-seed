package com.jd.seed.util.httpClient;

import static org.springframework.util.Assert.isTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * <pre>
 * httpClient junit
 * </pre>
 * 
 * @author mecarlen 2017年11月24日 下午3:40:01
 */
public class JunitHttpClient {
	// client
	private OkHttpClient client = null;

	@Before
	public void setup() {
		client = new OkHttpClient();
	}

	@Test
	public void get() throws IOException {
		Request request = new Request.Builder().url("http://seed.jd.com/dictionary/city/search/byId/3").get().build();
		Response response = client.newCall(request).execute();
		String str = response.body().string();
		isTrue(str != null, "请求失败" + response.message());
	}

	@Test
	public void post() throws IOException {
		String contentStr = "{\"zhName\":\"咸阳\",\"unityCode\":\"d_c_0901\",\"zhFullPin\":\"XianYan\",\"zhShortPin\":\"XY\",\"state\":\"DISABLE\",\"priority\":92}";
		RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), contentStr);
		Request request = new Request.Builder().url("http://seed.jd.com/dictionary/city/create").post(body).build();
		Response response = client.newCall(request).execute();
		Long id = Long.valueOf(response.body().string());
		isTrue(id > 0, "请求失败" + response.message());
	}
}
