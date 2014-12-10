package com.jwzhangjie.andbase.doc;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.jwzhangjie.andbase.R;
import com.jwzhangjie.andbase.doc.bean.AffixForShowBean;
import com.jwzhangjie.andbase.ui.base.BaseActivity;
import com.jwzhangjie.andbase.util.JieContant;
import com.jwzhangjie.andbase.widget.HackyViewPager;
import com.jwzhangjie.andbase.widget.TitleNavigate;
import com.jwzhangjie.andbase.widget.TitleNavigate.NavigateListener;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * title: PotoViewPagerUsed.java
 * @author jwzhangjie
 * Date: 2014年12月9日 上午11:13:22
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:点击显示大图，缩放功能，需要从其他界面接收点击图片的位置，以及图片列表
 */
@ContentView(R.layout.activity_photo_viewpager)
public class PotoViewPagerUsed extends BaseActivity implements NavigateListener{

	@ViewInject(R.id.view_pager)
	private HackyViewPager mViewPager;
	@ViewInject(R.id.title_navigate)
	private TitleNavigate titleNavigate;

	private List<AffixForShowBean> imgList;
	private int firstPosition = 0;
	private String title = "图片详情";

	DisplayImageOptions options;
	ImageLoader imageLoader = ImageLoader.getInstance();

	@Override
	protected void beforeCreate() {
		super.beforeCreate();
		Bundle bundle = getIntent().getExtras();
		title = bundle.getString("title");
		firstPosition = bundle.getInt("firstPosition", 0);
		imgList = bundle.getParcelableArrayList("imgList");
	}

	@Override
	protected void initListener() {
		super.initListener();
		titleNavigate.setNavigateListener(this);
	}


	@Override
	protected void initData() {
		super.initData();
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher)
				.cacheInMemory(false).cacheOnDisk(true)
				.considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565)
				.build();
		titleNavigate.setMiddleText(title);
		mViewPager.setAdapter(new PhotoPagerAdapter());
		mViewPager.setCurrentItem(firstPosition);
	}

	class PhotoPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imgList == null ? 0 : imgList.size();
		}

		@Override
		public View instantiateItem(ViewGroup container, int position) {
			PhotoView photoView = new PhotoView(container.getContext());
			AffixForShowBean bean = imgList.get(position);
			imageLoader.displayImage(
					JieContant.URL_Img_Base + bean.getAffixFolder(), photoView,
					options);
			// Now just add PhotoView to ViewPager and return it
			container.addView(photoView, LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			return photoView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

	}

	@Override
	public void navigateOnClick(View view) {
		if (view == titleNavigate.getLeftView()) {
			finish();
		}
	}
}
