package com.jwzhangjie.andbase.doc;

import org.apache.http.Header;

import com.jwzhangjie.andbase.net.DefaultJsonResponseHandler;
import com.jwzhangjie.andbase.net.JieHttpClient;
import com.loopj.android.http.RequestParams;


/**
 * title: HttpRequestUsed.java
 * @author jwzhangjie
 * Date: 2014-12-6 下午3:16:10
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description: 网络请求实例
 */
public class HttpRequestUsed {
	
	public HttpRequestUsed(){
		RequestParams params = new RequestParams();
		params.put("name", "jwzhangjie");
		params.put("pwd", "****");
		LoginRequest("/login", params);
	}

	/**
	 * 
	 * Title: LoginRequest
	 * Description: Post方式请求服务器数据
	 * @param url
	 * @param params
	 */
	public void LoginRequest(String url, RequestParams params){
		JieHttpClient.postBase(url, params, new DefaultJsonResponseHandler(){

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseBody, Throwable throwable) {
				super.onFailure(statusCode, headers, responseBody, throwable);
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseBody) {
				super.onSuccess(statusCode, headers, responseBody);
			}
			
		});
	}
}
