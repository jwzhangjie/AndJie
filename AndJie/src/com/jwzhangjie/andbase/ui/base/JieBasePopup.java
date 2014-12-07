package com.jwzhangjie.andbase.ui.base;

import com.jwzhangjie.andbase.JieApp;
import com.jwzhangjie.andbase.R;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.PopupWindow;


/**
 * title: JieBasePopup.java
 * @author jwzhangjie
 * Date: 2014-12-7 下午9:09:10
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:封装基础的弹出框
 */
public abstract class JieBasePopup extends BaseView {

	protected PopupWindow mPopupWindow;
	private View parentView;

	public JieBasePopup() {
		super();
	}

	public void initPopView(View parent) {
		parentView = parent;
	}

	public void initPopView(View parent, int viewId) {
		parentView = parent;
	}

	public void initPopWindow(int width, int height) {
		if (mPopupWindow == null) {
			mPopupWindow = new PopupWindow(view, width, height);
			/* 设置触摸外面时消失 */
			mPopupWindow.setOutsideTouchable(true);
			mPopupWindow.setBackgroundDrawable(JieApp.instance
					.getResources()
					.getDrawable(R.drawable.pop_select_catory_bg));
			/* 设置系统动画 */
			mPopupWindow.setAnimationStyle(R.style.popupAnimation);
			mPopupWindow.setTouchable(true);
			/* 设置点击menu以外其他地方以及返回键退出 */
			mPopupWindow.setFocusable(true);
		}
	}

	@SuppressWarnings("deprecation")
	public void initPopWindow(int width, int height, int anim) {
		if (mPopupWindow == null) {
			mPopupWindow = new PopupWindow(view, width, height);
			mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
			/* 设置触摸外面时消失 */
			mPopupWindow.setOutsideTouchable(true);
			/* 设置系统动画 */
			if (anim != -1) {
				mPopupWindow.setAnimationStyle(anim);
			}
			mPopupWindow.setTouchable(true);
			/* 设置点击menu以外其他地方以及返回键退出 */
			mPopupWindow.setFocusable(true);
		}
	}

	public void initPopWindow(Drawable popBg, int width, int height) {
		if (mPopupWindow == null) {
			mPopupWindow = new PopupWindow(view, width, height);
			/* 设置背景显示 */
			if (popBg != null) {
				mPopupWindow.setBackgroundDrawable(popBg);
			}
			/* 设置触摸外面时消失 */
			mPopupWindow.setOutsideTouchable(true);
			/* 设置系统动画 */
			mPopupWindow.setAnimationStyle(R.style.popupAnimation);
			mPopupWindow.setTouchable(true);
			/* 设置点击menu以外其他地方以及返回键退出 */
			mPopupWindow.setFocusable(true);
		}
	}

	public void startPop(int gravity, int x, int y) {
		mPopupWindow.update();
		mPopupWindow.showAtLocation(parentView, gravity, x, y);
	}

	public void startPopDown() {
		mPopupWindow.update();
		mPopupWindow.showAsDropDown(parentView);
	}

}
