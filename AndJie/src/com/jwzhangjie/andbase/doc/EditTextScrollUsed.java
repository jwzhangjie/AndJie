package com.jwzhangjie.andbase.doc;

import android.widget.ScrollView;

import com.jwzhangjie.andbase.R;
import com.jwzhangjie.andbase.ui.base.BaseActivity;
import com.jwzhangjie.andbase.widget.InnerScrollView;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;


/**
 * title: EditTextScrollUsed.java
 * @author jwzhangjie
 * Date: 2014-12-7 上午11:03:58
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:测试在ScrollView下可以滑动的EditText，需要child_scroll.parentScrollView = parent_scroll;
 */
@ContentView(R.layout.activity_scrollview_edittext)
public class EditTextScrollUsed extends BaseActivity {

	@ViewInject(R.id.parent_scroll)
	private ScrollView parent_scroll;
	@ViewInject(R.id.child_scroll)
	private InnerScrollView child_scroll;

	@Override
	protected void initView() {
		super.initView();
		child_scroll.parentScrollView = parent_scroll;
	}

}
