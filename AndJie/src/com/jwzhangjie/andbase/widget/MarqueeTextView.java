package com.jwzhangjie.andbase.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * title: MarqueeTextView.java
 * @author jwzhangjie
 * Date: 2014-12-7 上午10:45:23
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:跑马灯效果
 * android:singleLine="true"
 * android:ellipsize="marquee"
 * android:focusable="true"
 * android:focusableInTouchMode="true"
 * android:scrollHorizontally="true"
 * android:marqueeRepeatLimit="marquee_forever"
 */
public class MarqueeTextView extends TextView {

	public MarqueeTextView(Context context) {
		super(context);
	}

	public MarqueeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean isFocused() {
		return true;
	}

}
