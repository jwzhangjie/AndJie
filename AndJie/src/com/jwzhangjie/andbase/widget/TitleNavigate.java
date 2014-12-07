package com.jwzhangjie.andbase.widget;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import com.jwzhangjie.andbase.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * title: TitleNavigate.java
 * @author jwzhangjie
 * Date: 2014-12-7 上午11:15:23
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description: 自定义通用的标题栏
 */
public class TitleNavigate extends RelativeLayout {

	@ViewInject(R.id.leftView)
	private TextView leftView;
	@ViewInject(R.id.middleView)
	private TextView middleView;
	@ViewInject(R.id.rightView)
	private TextView rightView;
	@ViewInject(R.id.rightView_1)
	private TextView rightView_1;
	private Context context;
	private View view;

	public interface NavigateListener {
		public void navigateOnClick(View view);
	}

	public NavigateListener navigateListener;

	public void setNavigateListener(NavigateListener navigateListener) {
		this.navigateListener = navigateListener;
	}

	public TitleNavigate(Context context) {
		this(context, null);
	}

	public TitleNavigate(Context context, AttributeSet attrs) {
		super(context, attrs);
		initLayout();
	}

	private void initLayout() {
		context = getContext();
		view = LayoutInflater.from(context).inflate(R.layout.item_title_bar, null);
		ViewUtils.inject(this, view);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		addView(view, params);
	}

	public void setBg(int bg) {
		view.setBackgroundResource(bg);
	}

	@OnClick({ R.id.leftView, R.id.rightView, R.id.rightView_1 })
	public void onClick(View view) {
		if (navigateListener != null) {
			navigateListener.navigateOnClick(view);
		}
	}

	class leftClick implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			if (navigateListener != null) {
				navigateListener.navigateOnClick(v);
			}
		}
	}

	class rightClick implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			if (navigateListener != null) {
				navigateListener.navigateOnClick(v);
			}
		}

	}

	public TextView getLeftView() {
		return this.leftView;
	}

	public void setLeftText(String text) {
		leftView.setText(text);
	}

	public void setLeftText(int text) {
		leftView.setText(text);
	}

	/**
	 * @param drawable
	 * @param orientation
	 *            left:0 top:1 right:2 bottom:3
	 */
	public void setLeftImg(int drawable, int orientation) {
		Drawable img = getResources().getDrawable(drawable);
		// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
		img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
		switch (orientation) {
		case 0:
			leftView.setCompoundDrawables(img, null, null, null);
			break;
		case 1:
			leftView.setCompoundDrawables(null, img, null, null);
			break;
		case 2:
			leftView.setCompoundDrawables(null, null, img, null);
			break;
		case 3:
			leftView.setCompoundDrawables(null, null, null, img);
			break;
		}
	}

	public void setMiddleText(String text) {
		middleView.setText(text);
	}

	public void setMiddleText(int text) {
		middleView.setText(text);
	}

	/**
	 * @param drawable
	 * @param orientation
	 *            left:0 top:1 right:2 bottom:3
	 */
	public void setMiddleImg(int drawable, int orientation) {
		Drawable img = getResources().getDrawable(drawable);
		// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
		img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
		switch (orientation) {
		case 0:
			middleView.setCompoundDrawables(img, null, null, null);
			break;
		case 1:
			middleView.setCompoundDrawables(null, img, null, null);
			break;
		case 2:
			middleView.setCompoundDrawables(null, null, img, null);
			break;
		case 3:
			middleView.setCompoundDrawables(null, null, null, img);
			break;
		}
	}

	public void setLeftVisiable(int invisible) {
		leftView.setVisibility(invisible);
	}

	public void setRightText(int resid) {
		rightView.setText(resid);
	}

	public void setRightText(String text) {
		rightView.setText(text);
	}

	public TextView getRightView() {
		return this.rightView;
	}

	public void setRightImg(int drawable, int orientation) {
		Drawable img = getResources().getDrawable(drawable);
		// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
		img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
		switch (orientation) {
		case 0:
			rightView.setCompoundDrawables(img, null, null, null);
			break;
		case 1:
			rightView.setCompoundDrawables(null, img, null, null);
			break;
		case 2:
			rightView.setCompoundDrawables(null, null, img, null);
			break;
		case 3:
			rightView.setCompoundDrawables(null, null, null, img);
			break;
		}
	}

	public View getRightView1() {
		return rightView_1;
	}

	public void setRightView1(int drawable, int orientation) {
		Drawable img = getResources().getDrawable(drawable);
		// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
		img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
		switch (orientation) {
		case 0:
			rightView_1.setCompoundDrawables(img, null, null, null);
			break;
		case 1:
			rightView_1.setCompoundDrawables(null, img, null, null);
			break;
		case 2:
			rightView_1.setCompoundDrawables(null, null, img, null);
			break;
		case 3:
			rightView_1.setCompoundDrawables(null, null, null, img);
			break;
		}
	}

	public void setRightView1Show(boolean opt) {
		if (opt) {
			rightView_1.setVisibility(View.VISIBLE);
		} else {
			rightView_1.setVisibility(View.GONE);
		}
	}
}
