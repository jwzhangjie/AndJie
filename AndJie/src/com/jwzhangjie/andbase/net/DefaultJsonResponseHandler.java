package com.jwzhangjie.andbase.net;

import org.apache.http.Header;

import com.loopj.android.http.TextHttpResponseHandler;

/**
 * title: DefaultJsonResponseHandler.java
 * @author jwzhangjie
 * Date: 2014-12-6 下午3:03:51
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:处理服务器回调接口
 */
public class DefaultJsonResponseHandler extends TextHttpResponseHandler {

	public DefaultJsonResponseHandler() {

	}

	@Override
	public void onFailure(int statusCode, Header[] headers,
			String responseBody, Throwable throwable) {

	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, String responseBody) {

	}

}
