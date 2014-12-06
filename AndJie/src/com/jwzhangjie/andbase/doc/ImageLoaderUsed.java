package com.jwzhangjie.andbase.doc;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.jwzhangjie.andbase.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * title: ImageLoaderUsed.java
 * @author jwzhangjie
 * Date: 2014-12-6 下午2:53:34
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:String imageUri = "http://site.com/image.png"; // from Web
 * String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
 * String imageUri = "content://media/external/audio/albumart/13"; // from content provider
 * String imageUri = "assets://image.png"; // from assets
 * String imageUri = "drawable://" + R.drawable.image; // from drawables (only images, non-9patch)
 */
public class ImageLoaderUsed {

	ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;

	public ImageLoaderUsed() {
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher).cacheInMemory(false)
				.cacheOnDisk(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
	}

	/**
	 * Title: loadImg
	 * Description:显示网络图片
	 * @param url
	 * @param imageView
	 */
	public void loadImg(String url, ImageView imageView) {
		imageLoader.displayImage(url, imageView, options);
	}
	
/**
 * Quick Setup
1. Include library
Manual:

•Download JAR
•Put the JAR in the libs subfolder of your Android project
or

Maven dependency:

<dependency>
    <groupId>com.nostra13.universalimageloader</groupId>
    <artifactId>universal-image-loader</artifactId>
    <version>1.8.6</version>
</dependency>2. Android Manifest
<manifest>
    <uses-permission android:name="android.permission.INTERNET" />
    <!-- Include next permission if you want to allow UIL to cache images on SD card -->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    ...
    <application android:name="MyApplication">
        ...
    </application>
</manifest>3. Application class
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Create global configuration and initialize ImageLoader with this configuration
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
            ...
            .build();
        ImageLoader.getInstance().init(config);
    }
}Configuration and Display Options
•ImageLoader Configuration (ImageLoaderConfiguration) is global for application.
•Display Options (DisplayImageOptions) are local for every display task (ImageLoader.displayImage(...)).
Configuration
All options in Configuration builder are optional. Use only those you really want to customize.
See default values for config options in Java docs for every option.

// DON'T COPY THIS CODE TO YOUR PROJECT! This is just example of ALL options using.
File cacheDir = StorageUtils.getCacheDirectory(context);
ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
        .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
        .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null)
        .taskExecutor(...)
        .taskExecutorForCachedImages(...)
        .threadPoolSize(3) // default
        .threadPriority(Thread.NORM_PRIORITY - 1) // default
        .tasksProcessingOrder(QueueProcessingType.FIFO) // default
        .denyCacheImageMultipleSizesInMemory()
        .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
        .memoryCacheSize(2 * 1024 * 1024)
        .memoryCacheSizePercentage(13) // default
        .discCache(new UnlimitedDiscCache(cacheDir)) // default
        .discCacheSize(50 * 1024 * 1024)
        .discCacheFileCount(100)
        .discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
        .imageDownloader(new BaseImageDownloader(context)) // default
        .imageDecoder(new BaseImageDecoder()) // default
        .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
        .writeDebugLogs()
        .build();Display Options
Display Options can be applied to every display task (ImageLoader.displayImage(...) call).

Note: If Display Options wasn't passed to ImageLoader.displayImage(...)method then default Display Options from configuration (ImageLoaderConfiguration.defaultDisplayImageOptions(...)) will be used.

// DON'T COPY THIS CODE TO YOUR PROJECT! This is just example of ALL options using.
DisplayImageOptions options = new DisplayImageOptions.Builder()
        .showImageOnLoading(R.drawable.ic_stub) // resource or drawable
        .showImageForEmptyUri(R.drawable.ic_empty) // resource or drawable
        .showImageOnFail(R.drawable.ic_error) // resource or drawable
        .resetViewBeforeLoading(false)  // default
        .delayBeforeLoading(1000)
        .cacheInMemory(false) // default
        .cacheOnDisc(false) // default
        .preProcessor(...)
        .postProcessor(...)
        .extraForDownloader(...)
        .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
        .bitmapConfig(Bitmap.Config.ARGB_8888) // default
        .decodingOptions(...)
        .displayer(new SimpleBitmapDisplayer()) // default
        .handler(new Handler()) // default
        .build();Usage
Acceptable URIs examples
String imageUri = "http://site.com/image.png"; // from Web
String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
String imageUri = "content://media/external/audio/albumart/13"; // from content provider
String imageUri = "assets://image.png"; // from assets
String imageUri = "drawable://" + R.drawable.image; // from drawables (only images, non-9patch)NOTE: Use drawable:// only if you really need it! Always consider the native way to load drawables - ImageView.setImageResource(...) instead of using of ImageLoader.

Simple
// Load image, decode it to Bitmap and display Bitmap in ImageView
imageLoader.displayImage(imageUri, imageView);// Load image, decode it to Bitmap and return Bitmap to callback
imageLoader.loadImage(imageUri, new SimpleImageLoadingListener() {
    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        // Do whatever you want with Bitmap
    }
});Complete
// Load image, decode it to Bitmap and display Bitmap in ImageView
imageLoader.displayImage(imageUri, imageView, displayOptions, new ImageLoadingListener() {
    @Override
    public void onLoadingStarted(String imageUri, View view) {
        ...
    }
    @Override
    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
        ...
    }
    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        ...
    }
    @Override
    public void onLoadingCancelled(String imageUri, View view) {
        ...
    }
});// Load image, decode it to Bitmap and return Bitmap to callback
ImageSize targetSize = new ImageSize(120, 80); // result Bitmap will be fit to this size
imageLoader.loadImage(imageUri, targetSize, displayOptions, new SimpleImageLoadingListener() {
    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        // Do whatever you want with Bitmap
    }
});ImageLoader Helpers
Other useful methods and classes to consider.

ImageLoader |
            | - getMemoryCache()
            | - clearMemoryCache()
            | - getDiscCache()
            | - clearDiscCache()
            | - denyNetworkDownloads(boolean)
            | - handleSlowNetwork(boolean)
            | - pause()
            | - resume()
            | - stop()
            | - destroy()
            | - getLoadingUriForView(ImageView)
            | - cancelDisplayTask(ImageView)

MemoryCacheUtil |
                | - findCachedBitmapsForImageUri(...)
                | - findCacheKeysForImageUri(...)
                | - removeFromCache(...)

DiscCacheUtil |
              | - findInCache(...)
              | - removeFromCache(...)

StorageUtils |
             | - getCacheDirectory(Context)
             | - getIndividualCacheDirectory(Context)
             | - getOwnCacheDirectory(Context, String)

PauseOnScrollListener
Also look into more detailed Library Map

Useful Info
1.Caching is NOT enabled by default. If you want loaded images will be cached in memory and/or on disc then you should enable caching in DisplayImageOptions this way:
// Create default options which will be used for every 
//  displayImage(...) call if no options will be passed to this method
DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
            ...
            .cacheInMemory(true)
            .cacheOnDisc(true)
            ...
            .build();
ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
            ...
            .defaultDisplayImageOptions(defaultOptions)
            ...
            .build();
ImageLoader.getInstance().init(config); // Do it on Application start// Then later, when you want to display image
ImageLoader.getInstance().displayImage(imageUrl, imageView); // Default options will be usedor this way:

DisplayImageOptions options = new DisplayImageOptions.Builder()
            ...
            .cacheInMemory(true)
            .cacheOnDisc(true)
            ...
            .build();
ImageLoader.getInstance().displayImage(imageUrl, imageView, options); // Incoming options will be used1.If you enabled disc caching then UIL try to cache images on external storage (/sdcard/Android/data/[package_name]/cache). If external storage is not available then images are cached on device's filesytem. To provide caching on external storage (SD card) add following permission to AndroidManifest.xml:
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>1.How UIL define Bitmap size needed for exact ImageView? It searches defined parameters:

◦Get actual measured width and height of ImageView
◦Get android:layout_width and android:layout_height parameters
◦Get android:maxWidth and/or android:maxHeight parameters
◦Get maximum width and/or height parameters from configuration (memoryCacheExtraOptions(int, int) option)
◦Get width and/or height of device screen
So try to set android:layout_width|android:layout_height or android:maxWidth|android:maxHeight parameters for ImageView if you know approximate maximum size of it. It will help correctly compute Bitmap size needed for this view and save memory.

2.If you often got OutOfMemoryError in your app using Universal Image Loader then try next (all of them or several):

◦Reduce thread pool size in configuration (.threadPoolSize(...)). 1 - 5 is recommended.
◦Use .bitmapConfig(Bitmap.Config.RGB_565) in display options. Bitmaps in RGB_565 consume 2 times less memory than in ARGB_8888.
◦Use .memoryCache(new WeakMemoryCache()) in configuration or disable caching in memory at all in display options (don't call .cacheInMemory()).
◦Use .imageScaleType(ImageScaleType.IN_SAMPLE_INT) in display options. Or try .imageScaleType(ImageScaleType.EXACTLY).
◦Avoid using RoundedBitmapDisplayer. It creates new Bitmap object with ARGB_8888 config for displaying during work.
3.For memory cache configuration (ImageLoaderConfiguration.memoryCache(...)) you can use already prepared implementations.

◦Cache using only strong references: 
■LruMemoryCache (Least recently used bitmap is deleted when cache size limit is exceeded) - Used by default for API >= 9 
◦Caches using weak and strong references: 
■UsingFreqLimitedMemoryCache (Least frequently used bitmap is deleted when cache size limit is exceeded)
■LRULimitedMemoryCache (Least recently used bitmap is deleted when cache size limit is exceeded) - Used by default for API < 9 
■FIFOLimitedMemoryCache (FIFO rule is used for deletion when cache size limit is exceeded)
■LargestLimitedMemoryCache (The largest bitmap is deleted when cache size limit is exceeded)
■LimitedAgeMemoryCache (Decorator. Cached object is deleted when its age exceeds defined value)
◦Cache using only weak references: 
■WeakMemoryCache (Unlimited cache)
4.For disc cache configuration (ImageLoaderConfiguration.discCache(...)) you can use already prepared implementations:

◦UnlimitedDiscCache (The fastest cache, doesn't limit cache size) - Used by default 
◦TotalSizeLimitedDiscCache (Cache limited by total cache size. If cache size exceeds specified limit then file with the most oldest last usage date will be deleted)
◦FileCountLimitedDiscCache (Cache limited by file count. If file count in cache directory exceeds specified limit then file with the most oldest last usage date will be deleted. Use it if your cached files are of about the same size.)
◦LimitedAgeDiscCache (Size-unlimited cache with limited files' lifetime. If age of cached file exceeds defined limit then it will be deleted from cache.)
NOTE: UnlimitedDiscCache is 30%-faster than other limited disc cache implementations.

5.To display bitmap (DisplayImageOptions.displayer(...)) you can use already prepared implementations: 

◦RoundedBitmapDisplayer (Displays bitmap with rounded corners)
◦FadeInBitmapDisplayer (Displays image with "fade in" animation)
6.To avoid list (grid, ...) scrolling lags you can use PauseOnScrollListener:

boolean pauseOnScroll = false; // or true
boolean pauseOnFling = true; // or false
PauseOnScrollListener listener = new PauseOnScrollListener(imageLoader, pauseOnScroll, pauseOnFling);
listView.setOnScrollListener(listener);
	 */

}
