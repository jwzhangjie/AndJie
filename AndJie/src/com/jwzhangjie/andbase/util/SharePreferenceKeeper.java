package com.jwzhangjie.andbase.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * title: SharePreferenceKeeper.java
 * @author jwzhangjie
 * Date: 2014-12-6 下午3:22:27
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description: Share preference utils, used for reading and saving share preference conveniently.
 */
public class SharePreferenceKeeper {
	private static final String PREFERENCES_NAME = "preference";
	private static final String PREFERENCES_PERSONAL_NAME = "preference_per";

	// private static final String PREFERENCES_OBJ_NAME = "preference_obj";

	public static final void keepStringValue(Context context, String key,
			String value) {
		keepStringValue(context, key, value, true);
	}

	public static final void keepStringValue(Context context, String key,
			String value, boolean isPersonal) {
		SharedPreferences pref = getSharedPreferences(context, isPersonal);
		Editor editor = pref.edit();
		editor.putString(key, value);
		SharePreferenceDelegate.commit(editor);
	}

	public static final void keepIntValue(Context context, String key, int value) {
		keepIntValue(context, key, value, true);
	}

	public static final void keepIntValue(Context context, String key,
			int value, boolean isPersonal) {
		SharedPreferences pref = getSharedPreferences(context, isPersonal);
		Editor editor = pref.edit();
		editor.putInt(key, value);
		SharePreferenceDelegate.commit(editor);
	}

	public static final void keepBooleanValue(Context context, String key,
			boolean value) {
		keepBooleanValue(context, key, value, true);
	}

	public static final void keepBooleanValue(Context context, String key,
			boolean value, boolean isPersonal) {
		SharedPreferences pref = getSharedPreferences(context, isPersonal);
		Editor editor = pref.edit();
		editor.putBoolean(key, value);
		SharePreferenceDelegate.commit(editor);
	}

	public static final String getStringValue(Context context, String key) {
		return getStringValue(context, key, null);
	}

	public static final String getStringValue(Context context, String key,
			boolean isPersonal) {
		return getStringValue(context, key, null, isPersonal);
	}

	public static final String getStringValue(Context context, String key,
			String defValue) {
		return getStringValue(context, key, defValue, true);
	}

	public static final String getStringValue(Context context, String key,
			String defValue, boolean isPersonal) {
		SharedPreferences pref = getSharedPreferences(context, isPersonal);
		return pref.getString(key, defValue);
	}

	public static final int getIntValue(Context context, String key) {
		return getIntValue(context, key, 0);
	}

	public static final int getIntValue(Context context, String key,
			boolean isPersonal) {
		return getIntValue(context, key, 0, isPersonal);
	}

	public static final int getIntValue(Context context, String key,
			int defValue) {
		return getIntValue(context, key, defValue, true);
	}

	public static final int getIntValue(Context context, String key,
			int defValue, boolean isPersonal) {
		SharedPreferences pref = getSharedPreferences(context, isPersonal);
		return pref.getInt(key, defValue);
	}

	public static final boolean getBooleanValue(Context context, String key) {
		return getBooleanValue(context, key, false, true);
	}

	public static final boolean getBooleanValue(Context context, String key,
			boolean defValue, boolean isPersonal) {
		SharedPreferences pref = getSharedPreferences(context, isPersonal);
		return pref.getBoolean(key, defValue);
	}

	public static void removeKey(Context context, String key) {
		removeKey(context, key, true);
	}

	public static void removeKey(Context context, String key, boolean isPersonal) {
		SharedPreferences pref = getSharedPreferences(context, isPersonal);
		Editor editor = pref.edit();
		editor.remove(key);
		SharePreferenceDelegate.commit(editor);
	}

	public static void clearAll(Context context) {
		clearPersonal(context);

		SharedPreferences pref = getSharedPreferences(context, false);
		Editor editor = pref.edit();
		editor.clear();
		SharePreferenceDelegate.commit(editor);
	}

	public static void clearPersonal(Context context) {
		SharedPreferences pref = getSharedPreferences(context, true);
		Editor editor = pref.edit();
		editor.clear();
		SharePreferenceDelegate.commit(editor);
	}

	private static SharedPreferences getSharedPreferences(Context context,
			boolean isPersonal) {
		if (isPersonal) {
			return context.getSharedPreferences(PREFERENCES_PERSONAL_NAME,
					Context.MODE_PRIVATE);
		} else {
			return context.getSharedPreferences(PREFERENCES_NAME,
					Context.MODE_PRIVATE);
		}
	}
}
