package com.jwzhangjie.andbase.ui.dialog;

import com.jwzhangjie.andbase.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * title: WaitingFragmentDialog.java
 * 
 * @author jwzhangjie 
 * Date: 2014-12-9 下午9:16:04 version 1.0 
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:FragmentDialog主要用在FragmentActivity
 */
public class WaitingFragmentDialog extends DialogFragment {

	public WaitingFragmentDialog() {
		setStyle(-1, R.style.waiting_dialog);
	}

	@Override
	public void onActivityCreated(Bundle bundle) {
		super.onActivityCreated(bundle);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog_waiting, container, false);
		return view;
	}

	@Override
	public void show(FragmentManager manager, String tag) {
		super.show(manager, tag);
	}
	
	/**
	 * Title: show
	 * Description:android.support.v4.app.FragmentManager.getSupportFragmentManager()
	 * @param manager
	 */
	public void show(FragmentManager manager){
		show(manager, "waitingDialog");
	}
	
}
