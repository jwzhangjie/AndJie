package com.jwzhangjie.andbase.doc;

import java.util.List;

import org.apache.http.Header;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemClickListener;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jwzhangjie.andbase.R;
import com.jwzhangjie.andbase.doc.adapter.AccountAdapter;
import com.jwzhangjie.andbase.doc.bean.GsonBean;
import com.jwzhangjie.andbase.net.DefaultJsonResponseHandler;
import com.jwzhangjie.andbase.net.JieHttpClient;
import com.jwzhangjie.andbase.ui.base.BaseActivity;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.loopj.android.http.RequestParams;


/**
 * title: RecyclerViewUsed.java
 * @author jwzhangjie
 * Date: 2014-12-6 下午4:48:20
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:综合演示了RecyclerView的基本使用，Gson解析，数据请求的综合实例
 */
@ContentView(R.layout.activity_recyclerview)
public class RecyclerViewUsed extends BaseActivity{
	
	@ViewInject(R.id.recyclerViewId)
	private RecyclerView mRecyclerView;
	
	private AccountAdapter mAccountAdapter;
	
	private List<GsonBean> listAccounts;

	@Override
	protected void initView() {
		super.initView();
		//这个是必须的
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		// 设置布局管理器
		mRecyclerView.setLayoutManager(layoutManager);
	}
	
	
	@Override
	protected void initListener() {
		super.initListener();
		//这个功能是我额外添加的
		mRecyclerView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(View view, int position) {
				
			}
		});
	}



	@Override
	protected void initData() {
		super.initData();
		mAccountAdapter = new AccountAdapter();
		mRecyclerView.setAdapter(mAccountAdapter);
		getAcccountInfo("url");
	}
	
	private void getAcccountInfo(String url){
		RequestParams params = new RequestParams();
		params.put("name", "jwzhangjie");
		params.put("pwd", "****");
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
				Gson gson = new Gson();
				listAccounts = gson.fromJson(responseBody, new TypeToken<List<GsonBean>>(){}.getType());
				mAccountAdapter.setContent(listAccounts);
			}
			
		});
	}
	
}
