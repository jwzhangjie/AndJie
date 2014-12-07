package com.jwzhangjie.andbase.doc;

import android.view.View;

import com.jwzhangjie.andbase.R;
import com.jwzhangjie.andbase.ui.base.BaseActivity;
import com.jwzhangjie.andbase.widget.TitleNavigate;
import com.jwzhangjie.andbase.widget.TitleNavigate.NavigateListener;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;


/**
 * title: NavigateUsed.java
 * @author jwzhangjie
 * Date: 2014-12-7 上午11:22:26
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:演示标题栏的基本使用
 */
@ContentView(R.layout.activity_scrollview_edittext)
public class NavigateUsed extends BaseActivity{

	@ViewInject(R.id.nav_title_bar)
	private TitleNavigate nav_title_bar;

	@Override
	protected void initData() {
		super.initData();
		nav_title_bar.setMiddleText("自定义通用标题栏");
		nav_title_bar.setLeftImg(R.drawable.icon_navigate, 0);
	}

	@Override
	protected void initListener() {
		super.initListener();
		nav_title_bar.setNavigateListener(new NavigateListener() {
			
			@Override
			public void navigateOnClick(View view) {
				if (nav_title_bar.getLeftView() == view) {
					showInfo("你点击了返回");
				}else if (nav_title_bar.getRightView() == view) {
					showInfo("你点击了右边按钮");
				}
			}
		});
	}
	
	
}
