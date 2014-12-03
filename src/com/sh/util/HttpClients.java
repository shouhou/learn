package com.sh.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpClients {
	
	public static String get(final String uri, Map<String, String> params) {
		return send(uri, HttpGet.METHOD_NAME, params);
	}
	
	public static String post(final String uri, Map<String, String> params) {
		return send(uri, HttpPost.METHOD_NAME, params);
	}

	private static String send(final String uri, final String method, Map<String, String> params) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse resp = null;
		try {
			Map<String, String> data = new HashMap<String, String>();
			data.put("mark", "a");
			
			RequestBuilder builder = RequestBuilder.create(method);
			for(String key : params.keySet()) {
				builder.addParameter(key, params.get(key));
			}
			HttpUriRequest req = builder.setUri(uri).build();
			System.err.println("构建"+req.getMethod()+"请求完成，开始发送HTTP请求...");
			httpClient = org.apache.http.impl.client.HttpClients.createDefault();
			resp = httpClient.execute(req);
			
			System.err.println("HTTP请求发送完成，开始检查HTTP响应状态...");
			StatusLine sc  = resp.getStatusLine();
			if(sc.getStatusCode() != HttpStatus.SC_OK) {
				System.err.println("HTTP响应状态‘{}’异常，退出处理：" + sc.getStatusCode());
			}
			
			System.err.println("HTTP响应状态‘{}’正常，开始业务逻辑处理：" +  sc.getStatusCode());
			HttpEntity entity = resp.getEntity();
			System.err.println("HTTP响应内容长度：" + entity.getContentLength());
			String result = EntityUtils.toString(entity).trim();
			EntityUtils.consumeQuietly(entity);
			System.err.println("HTTP响应内容：" + result);
			
			return result;
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(resp);
			IOUtils.closeQuietly(httpClient);
		}
		return null;
	}

	public static void main(String args[]){
		
		
	}
}
