package com.jwzhangjie.andbase.ui.base;

import com.jwzhangjie.andbase.interfaces.IActivity;
import com.jwzhangjie.andbase.util.JieContant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;


/**
 * title: BaseFragment.java
 * @author jwzhangjie
 * Date: 2014-12-6 下午1:51:19
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:Fagment基础类
 */
public class BaseFragment extends Fragment implements IActivity {

	protected View view;
	protected FragmentActivity activity;
	protected Intent startIntent;
	public int PULL_FINISH = 0;
	public boolean refresh = false;
	public String ISREFRESH = "isReFresh";
	private Toast mToast;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = getActivity();
		startIntent = new Intent();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initView();
		initListener();
		initData();
	}

	protected View findViewById(int id) {
		return view.findViewById(id);
	}

	protected void initView() {

	}

	protected void initListener() {

	}

	protected void initData() {

	}
	
	/**
	 * Title: updateNet
	 * Description: Fragment切换时调用
	 * @param bundle 切换时传递参数用的
	 */
	public void updateNet(Bundle bundle) {

	}

	public void updateNet() {

	}

	protected void finish() {
		activity.finish();
	}

	protected void finish(Intent intent) {
		activity.setResult(JieContant.RESULT_OK, intent);
		activity.finish();
	}

	protected void showInfo(String text) {
		if (mToast == null) {
			mToast = Toast.makeText(activity, text, Toast.LENGTH_SHORT);
			mToast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			mToast.setText(text);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	protected void showInfo(int text) {
		if (mToast == null) {
			mToast = Toast.makeText(activity, text, Toast.LENGTH_SHORT);
			mToast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			mToast.setText(text);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	@Override
	public void startActivity(Class<?> cls, boolean isClose) {

	}

	@Override
	public void startActivity(Class<?> cls) {
		startIntent.setClass(activity, cls);
		startActivity(startIntent);
	}

	@Override
	public void startActivity(Class<?> cls, Bundle bundle, boolean isClose) {
		startIntent.setClass(activity, cls);
		startIntent.putExtras(bundle);
		startActivity(startIntent);
		if (isClose) {
			activity.finish();
		}
	}

	@Override
	public void startActivityForResult(int request) {

	}

	@Override
	public void startActivityForResult(int request, Class<?> cls,
			boolean isClose) {

	}

	@Override
	public void startActivityForResult(int request, Class<?> cls) {

	}

	@Override
	public void startActivityForResult(int request, Class<?> cls, Bundle bundle) {
		startIntent.setClass(activity, cls);
		startIntent.putExtras(bundle);
		super.startActivityForResult(startIntent, request);
	}

}
