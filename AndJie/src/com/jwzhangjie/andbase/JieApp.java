package com.jwzhangjie.andbase;

import com.jwzhangjie.andbase.ui.base.BaseActivity;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

public class JieApp extends Application {

	public static JieApp instance = null;
	private BaseActivity currentRunningActivity = null;
	private LayoutInflater mInflater;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		initImageLoader(instance);
	}

	public BaseActivity getCurrentRunningActivity() {
		return currentRunningActivity;
	}

	public void setCurrentRunningActivity(BaseActivity currentRunningActivity) {
		this.currentRunningActivity = currentRunningActivity;
	}

	public LayoutInflater getInflater() {
		if (mInflater == null) {
			mInflater = LayoutInflater.from(getApplicationContext());
		}
		return mInflater;
	}

	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
}
