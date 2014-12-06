package com.jwzhangjie.andbase.net;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.entity.StringEntity;

import android.content.Context;

import com.jwzhangjie.andbase.util.JieContant;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

/**
 * title: UEHttpClient.java
 * @author jwzhangjie
 * Date: 2014-12-6 下午3:07:32
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:使用android-async-http-1.4.4实现以后后台请求
 */
public class JieHttpClient {

	private static final String BASE_URL = JieContant.URL_Base;
	private static AsyncHttpClient client = new AsyncHttpClient();

	static {
		client.setTimeout(30000);
	}

	public static void get(String url, RequestParams params,
			DefaultJsonResponseHandler responseHandler) {
		client.get(getAbsUrl(url), params, responseHandler);
	}

	/**
	 * Title: postJson
	 * Description:上传参数采用json格式
	 * @param context
	 * @param url
	 * @param data
	 * @param responseHandler
	 */
	public static void postJson(Context context, String url, String data,
			DefaultJsonResponseHandler responseHandler) {
		StringEntity entity = null;
		if (data != null) {
			try {
				entity = new StringEntity(data);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		client.post(context, url, entity, "application/json", responseHandler);
	}

	public static void postBase(String url, RequestParams params,
			DefaultJsonResponseHandler responseHandler) {
		client.post(getAbsUrl(url), params, responseHandler);
	}

	public static void post(String url, RequestParams params,
			DefaultJsonResponseHandler responseHandler) {
		client.post(url, params, responseHandler);
	}

	/**
	 *
	 * Title: postFiles
	 * Description:上传文件
	 * @param url
	 * @param params
	 * @param listFile
	 * @param fileKey
	 * @param responseHandler
	 */
	public static void postFiles(String url, RequestParams params,
			List<File> listFile, String fileKey,
			DefaultJsonResponseHandler responseHandler) {
		client.post(getAbsUrl(url), params, listFile, fileKey, responseHandler);
	}

	public static String getAbsUrl(String string) {
		return BASE_URL + string;
	}

}
