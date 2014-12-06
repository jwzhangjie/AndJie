package com.jwzhangjie.andbase.doc;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jwzhangjie.andbase.doc.bean.GsonBean;
import com.jwzhangjie.andbase.util.AppLog;

/**
 * title: GsonUsed.java
 * @author jwzhangjie
 * Date: 2014-12-6 下午3:58:56
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:Gson的基本使用
 */
public class GsonUsed {
	
	public GsonUsed(){
		
	}
	
	/**
	 * 
	 * Title: parseObject
	 * Description:解析单个对象
	 * @param json
	 */
	public void parseObject(String json){
		Gson gson = new Gson();
		GsonBean mGsonBean = gson.fromJson(json, GsonBean.class);
		//这样就把json字符串转化为对象了，直接使用mGsonBean就可以了
		AppLog.e(mGsonBean.getUser_name());
	}
	
	/**
	 * 
	 * Title: parseArray
	 * Description:解析json数组
	 * @param json
	 */
	public void parseArray(String json){
		Gson gson = new Gson();
		List<GsonBean> list = gson.fromJson(json, new TypeToken<List<GsonBean>>(){}.getType());
		for (GsonBean gsonBean : list) {
			AppLog.e(gsonBean.getUser_name());
		}
	}
}
