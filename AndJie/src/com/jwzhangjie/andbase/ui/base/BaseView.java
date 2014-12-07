package com.jwzhangjie.andbase.ui.base;

import com.jwzhangjie.andbase.JieApp;

import android.view.View;
import android.widget.Toast;


/**
 * title: BaseView.java
 * @author jwzhangjie
 * Date: 2014-12-7 下午10:34:35
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:
 */
public class BaseView {
	protected View view;
	protected Toast mToast;

	public BaseView() {
	}

	public View findViewById(int id) {
		return view.findViewById(id);
	}

	protected void showInfo(int text) {
		if (mToast == null) {
			mToast = Toast.makeText(JieApp.instance, text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	protected void showInfo(String text) {
		if (mToast == null) {
			mToast = Toast.makeText(JieApp.instance, text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

}
