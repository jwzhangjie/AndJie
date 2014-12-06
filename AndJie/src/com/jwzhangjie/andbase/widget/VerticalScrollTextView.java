package com.jwzhangjie.andbase.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * title: VerticalScrollTextView.java
 * @author jwzhangjie
 * Date: 2014-12-6 下午3:28:28
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:文本内容垂直滚动，最好在xml里面设置一个初始文本
 */
public class VerticalScrollTextView extends TextView {

	private float step = 0f;
	private Paint mPaint;
	private String text;
	private float width;
	private List<String> textList = new ArrayList<String>(); // 分行保存textview的显示信息。
	StringBuilder builder = new StringBuilder();

	public VerticalScrollTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		text = getText().toString();
		mPaint = new Paint();
		mPaint.setTextSize(34);
	}

	public VerticalScrollTextView(Context context) {
		super(context);
	}

	/**
	 * Title: setText
	 * Description:动态设置滚动的文本内容
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
		requestLayout();
		invalidate();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		width = getMeasuredWidth();
		final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		if (widthMode != MeasureSpec.EXACTLY) {
			throw new IllegalStateException(
					"ScrollLayout only canmCurScreen run at EXACTLY mode!");
		}

		float length = 0;
		int count = text.length();
		if (text == null || count == 0) {
			return;
		}

		// 下面的代码是根据宽度和字体大小，来计算textview显示的行数。
		textList.clear();
		builder.delete(0, builder.length());
		for (int i = 0; i < count; i++) {
			if (length < width) {
				builder.append(text.charAt(i));
				length += mPaint.measureText(text.substring(i, i + 1));
				if (i == text.length() - 1) {
					textList.add(builder.toString());
				}
			} else {
				textList.add(builder.toString().substring(0,
						builder.toString().length() - 1));
				builder.delete(0, builder.length() - 1);
				length = mPaint.measureText(text.substring(i, i + 1));
				i--;
			}
		}
	}

	// 下面代码是利用上面计算的显示行数，将文字画在画布上，实时更新。
	@Override
	public void onDraw(Canvas canvas) {
		if (textList.size() == 0)
			return;
		for (int i = 0; i < textList.size(); i++) {
			canvas.drawText(textList.get(i), 5, this.getHeight() + (i + 1)
					* mPaint.getTextSize() - step, getPaint());
		}

		invalidate();
		step = step + 0.2f;
		if (step >= this.getHeight() + textList.size() * mPaint.getTextSize()) {
			step = 0;
		}
	}

}
