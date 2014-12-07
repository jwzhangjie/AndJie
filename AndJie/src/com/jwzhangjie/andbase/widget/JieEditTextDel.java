package com.jwzhangjie.andbase.widget;

import java.util.ArrayList;

import com.jwzhangjie.andbase.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * title: JieEditTextDel.java
 * @author jwzhangjie
 * Date: 2014-12-7 上午11:29:32
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:自定义组合框，分为左，中， 右 左边：一个TextView 中间：一个EditText 右边：一个ImageView，一个TextView
 * 				自动检测内容，如果有则出现删除图标
 */
public class JieEditTextDel extends LinearLayout {

	protected TextView lefText;
	protected EditText middleText;
	protected TextView rightText;
	protected ImageView rightImg;
	private Context context;
	protected int maxLen = 999;

	private boolean isDelEnable = true;

	private ArrayList<OnFocusChangeListener> mFocusListeners = new ArrayList<OnFocusChangeListener>();
	private ArrayList<TextWatcher> mTextWatchers = new ArrayList<TextWatcher>();
	private ArrayList<OnClickListener> mClickListeners = new ArrayList<OnClickListener>();
	private ArrayList<OnClearTextListener> mOnClearTextListeners = new ArrayList<OnClearTextListener>();
	private ArrayList<OnEditorActionListener> mOnEditorActionListener = new ArrayList<OnEditorActionListener>();

	private OnEditorActionListener mEditorActionListener = new OnEditorActionListener() {

		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			for (OnEditorActionListener l : mOnEditorActionListener) {
				l.onEditorAction(v, actionId, event);
				return true;
			}
			return false;
		}
	};

	private OnFocusChangeListener mFocusListener = new OnFocusChangeListener() {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			for (OnFocusChangeListener listener : mFocusListeners) {
				listener.onFocusChange(v, hasFocus);
			}

			if (hasFocus && getText().length() > 0 && isDelEnable) {
				rightImg.setVisibility(View.VISIBLE);
			} else if (isDelEnable) {
				rightImg.setVisibility(View.INVISIBLE);
			} else {
				rightImg.setVisibility(View.GONE);
			}
			invalidate();
		}
	};

	private TextWatcher mTextWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			for (TextWatcher watch : mTextWatchers) {
				watch.onTextChanged(s, start, before, count);
			}

			if (s.length() > 0 && isDelEnable) {
				rightImg.setVisibility(View.VISIBLE);
			} else if (isDelEnable) {
				rightImg.setVisibility(View.INVISIBLE);
			} else {
				rightImg.setVisibility(View.GONE);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			for (TextWatcher watch : mTextWatchers) {
				watch.beforeTextChanged(s, start, count, after);
			}
		}

		@Override
		public void afterTextChanged(Editable s) {
			for (TextWatcher watch : mTextWatchers) {
				watch.afterTextChanged(s);
			}
		}
	};

	private OnClickListener mEditTextClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			for (OnClickListener l : mClickListeners) {
				l.onClick(v);
			}
		}
	};

	private OnClickListener mDelClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			middleText.setText("");
			for (OnClearTextListener l : mOnClearTextListeners) {
				l.onClearText();
			}
		}
	};

	public interface OnClearTextListener {
		public void onClearText();
	}

	public JieEditTextDel(Context context) {
		super(context);
		initLayout();

	}

	public JieEditTextDel(Context context, AttributeSet attrs) {
		super(context, attrs);
		initLayout();
	}

	private View view;

	protected void initLayout() {
		context = getContext();
		view = LayoutInflater.from(context).inflate(R.layout.item_editext_del,
				null);
		lefText = (TextView) view.findViewById(R.id.leftText);
		middleText = (EditText) view.findViewById(R.id.middleText);
		rightText = (TextView) view.findViewById(R.id.rightText);
		rightImg = (ImageView) view.findViewById(R.id.right_img);
		middleText.setSingleLine(true);
		middleText.setEllipsize(TruncateAt.END);
		middleText.setClickable(true);
		addListener();
		addView(view);
	}

	protected void addListener() {
		rightImg.setOnClickListener(mDelClickListener);
		middleText.setOnClickListener(mEditTextClickListener);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		middleText.setOnFocusChangeListener(mFocusListener);
		middleText.addTextChangedListener(mTextWatcher);
		middleText.setOnEditorActionListener(mEditorActionListener);
	}

	@Override
	protected void onDetachedFromWindow() {
		middleText.setOnFocusChangeListener(null);
		middleText.removeTextChangedListener(mTextWatcher);
		middleText.setOnEditorActionListener(null);
		super.onDetachedFromWindow();
	}

	public void setError(CharSequence text) {
		middleText.setError(text);
	}

	public void setHint(int resId) {
		middleText.setHint(resId);
	}

	public void setHint(CharSequence text) {
		middleText.setHint(text);
	}

	public void setHintColor(int color) {
		middleText.setHintTextColor(color);
	}

	public CharSequence getHint() {
		return middleText.getHint();
	}

	public String getContent() {
		return getText().toString().trim();
	}

	public boolean isEmpty() {
		return TextUtils.isEmpty(getContent());
	}

	public void addOnFocusChangeListener(OnFocusChangeListener l) {
		mFocusListeners.add(l);
	}

	public boolean removeOnFocusChangeListener(OnFocusChangeListener l) {
		return mFocusListeners.remove(l);
	}

	public void addOnClearTextListener(OnClearTextListener l) {
		mOnClearTextListeners.add(l);
	}

	public void addOnEditorActionListener(OnEditorActionListener l) {
		mOnEditorActionListener.add(l);
	}

	public boolean removeOnClearTextListener(OnClearTextListener l) {
		return mOnClearTextListeners.remove(l);
	}

	public void addOnClickListener(OnClickListener l) {
		mClickListeners.add(l);
	}

	public boolean removeOnClickListener(OnClickListener l) {
		return mClickListeners.remove(l);
	}

	public void addTextChangedListener(TextWatcher watcher) {
		mTextWatchers.add(watcher);
	}

	public boolean removeTextChangedListener(TextWatcher watcher) {
		return mTextWatchers.remove(watcher);
	}

	public boolean isEditTextFocused() {
		return middleText.hasFocus();
	}

	public void setText(int resId) {
		middleText.setText(resId);
	}

	public void setText(CharSequence text) {
		middleText.setText(text);
	}

	public void setPassword() {
		middleText.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
	}

	public void setOnlyNumber() {
		middleText.setInputType(InputType.TYPE_CLASS_NUMBER);
	}
	
	public void setNumber(){
		middleText.setInputType(EditorInfo.TYPE_CLASS_PHONE|InputType.TYPE_CLASS_TEXT);
	}

	public void setNumberPwd() {
		middleText.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
	}

	public void setMaxLen(int len) {
		maxLen = len;
		middleText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
				len) });
	}

	public void setSelection(int index) {
		middleText.setSelection(index);
	}

	public void setFilters(InputFilter[] filters) {
		middleText.setFilters(filters);
	}

	public void setInputType(int type) {
		middleText.setInputType(type);
	}
	
	public void setEditTextTag(Object tag) {
		middleText.setTag(tag);
	}

	public void setTextSize(float size) {
		middleText.setTextSize(size);
	}

	// 设置文本显示的方向
	public void setTextLocal(int local) {
		middleText.setGravity(local);
	}

	public void setTextSize(int unit, float size) {
		middleText.setTextSize(unit, size);
	}

	public Editable getText() {
		return middleText.getText();
	}

	public void setLines(int lines) {
		middleText.setLines(lines);
	}
	
	public void setMaxLines(int lines){
		middleText.setMaxLines(lines);
	}

	public void setLeftText(String text) {
		lefText.setText(text);
	}

	public void setLeftMinEms(int minEms) {
		lefText.setMinEms(minEms);
	}

	public void setLeftText(int text) {
		lefText.setText(text);
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
			lefText.setCompoundDrawables(img, null, null, null);
			break;
		case 1:
			lefText.setCompoundDrawables(null, img, null, null);
			break;
		case 2:
			lefText.setCompoundDrawables(null, null, img, null);
			break;
		case 3:
			lefText.setCompoundDrawables(null, null, null, img);
			break;
		}
	}

	public void setRightText(String text) {
		if (rightText.getVisibility() == View.GONE) {
			rightText.setVisibility(View.VISIBLE);
		}
		rightText.setText(text);
	}

	public void setMiddleTextGravity(int gravity) {
		middleText.setGravity(gravity);
	}

	public void setRightText(int text) {
		if (rightText.getVisibility() == View.GONE) {
			rightText.setVisibility(View.VISIBLE);
		}
		rightText.setText(text);
	}

	public void setRightImg(int drawable) {
		if (rightImg.getVisibility() == View.GONE) {
			rightImg.setVisibility(View.VISIBLE);
		}
		rightImg.setImageResource(drawable);
	}

	public void RightImgStatus(boolean opt) {
		isDelEnable = opt;
		if (opt)
			rightImg.setVisibility(View.VISIBLE);
		else
			rightImg.setVisibility(View.GONE);
	}

	public void setReadOnly() {
		middleText.setKeyListener(null);
		middleText.setFocusable(false);
	}

	public void getFocus() {
		middleText.setFocusable(true);
		middleText.setFocusableInTouchMode(true);
		middleText.requestFocus();
	}

	public void setLeftHide() {
		lefText.setVisibility(View.GONE);
	}

	public void setRightTextColor(int color) {
		rightText.setTextColor(color);
	}

	public void setLeftTextColor(int color) {
		lefText.setTextColor(color);
	}

	public void setMiddleTextColor(int color) {
		middleText.setTextColor(color);
	}

	public void setEditEnable(boolean opt) {
		middleText.setEnabled(false);
	}

	public void setVisibility(int opt) {
		middleText.setVisibility(opt);
	}

	public void setHide(int opt) {
		setVisibility(opt);
	}

	public void setRightTextImg(int drawable) {
		if (rightText.getVisibility() == View.GONE) {
			rightText.setVisibility(View.VISIBLE);
		}
		rightText.setBackgroundResource(drawable);
	}

	public void setBg(int bg) {
		view.setBackgroundResource(bg);
	}
}
