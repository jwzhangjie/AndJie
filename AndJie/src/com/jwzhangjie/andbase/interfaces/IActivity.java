package com.jwzhangjie.andbase.interfaces;

import android.os.Bundle;

public interface IActivity {

	void startActivity(Class<?> cls, boolean isClose);

	void startActivity(Class<?> cls);

	void startActivity(Class<?> cls, Bundle bundle, boolean isClose);

	void startActivityForResult(int request);

	void startActivityForResult(int request, Class<?> cls, boolean isClose);

	void startActivityForResult(int request, Class<?> cls);

	void startActivityForResult(int request, Class<?> cls, Bundle bundle);
}
