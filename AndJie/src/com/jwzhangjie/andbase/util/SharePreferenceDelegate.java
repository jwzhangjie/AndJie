package com.jwzhangjie.andbase.util;

import android.annotation.SuppressLint;
import android.content.SharedPreferences.Editor;


/**
 * title: SharePreferenceDelegate.java
 * @author jwzhangjie
 * Date: 2014-12-6 下午3:22:22
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:
 */
public class SharePreferenceDelegate {

	@SuppressLint("NewApi")
	public static final void commit(Editor editor) {
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.GINGERBREAD) {
			editor.commit();
		} else {
			editor.apply();
		}
	}
}
