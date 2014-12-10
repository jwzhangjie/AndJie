package com.jwzhangjie.andbase.ui.dialog;

import com.jwzhangjie.andbase.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * title: WaitingDialog.java
 * @author jwzhangjie
 * Date: 2014-12-8 下午8:14:51
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:使用Dialog实现的，用于网络请求或者耗时操作显示
 */
public class WaitingDialog extends Dialog{

	private TextView waitingInfo;
	private ProgressBar waitingDialog;
	
	public WaitingDialog(Context context) {
		super(context, R.style.waiting_dialog);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_waiting);
		// setCancelable(false);
		setCanceledOnTouchOutside(false);
		waitingDialog = (ProgressBar)findViewById(R.id.waitingDialogId);
		waitingInfo = (TextView)findViewById(R.id.waitingInfoId);
	}
	

	public void showInfo(String str){
		waitingInfo.setText(str);
	}
	
	public void showInfo(int str){
		waitingInfo.setText(str);
	}
}
