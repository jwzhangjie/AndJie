package com.jwzhangjie.andbase.ui.base;

import java.util.HashMap;
import java.util.Map;

import com.jwzhangjie.andbase.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;


/**
 * title: BaseChangeFragments.java
 * @author jwzhangjie
 * Date: 2014-12-6 下午1:46:58
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:管理Fragment切换使用
 */
public class BaseChangeFragments extends BaseActivity {

	protected FragmentManager mFragmentManager;
	protected FragmentTransaction mFragmentTransaction;

	protected String mCurrentFragmentTag;
	protected Map<String, BaseFragment> mapFragments = new HashMap<String, BaseFragment>();

	@Override
	protected void beforeCreate() {
		super.beforeCreate();
		mFragmentManager = getSupportFragmentManager();
	}

	protected FragmentTransaction ensureTransaction() {
		if (mFragmentTransaction == null) {
			mFragmentTransaction = mFragmentManager.beginTransaction();
			// mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			// mFragmentTransaction.addToBackStack(null);
			mFragmentTransaction
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		}

		return mFragmentTransaction;
	}

	protected BaseFragment getFragment(String tag) {
		BaseFragment f = (BaseFragment) (mFragmentManager
				.findFragmentByTag(tag));
		if (f == null) {
			// 在这里判断tag,不同则实例化对应的fragment
			f = mapFragments.get(tag);
		}
		return f;
	}

	protected void attachFragment(int layout, Fragment f, String tag) {
		if (f != null) {
			if (f.isDetached()) {
				ensureTransaction();
				mFragmentTransaction.attach(f);
			} else if (!f.isAdded()) {
				ensureTransaction();
				mFragmentTransaction.add(layout, f, tag);
			}
		}
	}

	protected void detachFragment(Fragment f) {
		if (f != null && !f.isDetached()) {
			ensureTransaction();
			mFragmentTransaction.detach(f);
		}
	}

	/**
	 * @param layout
	 * @param f
	 * @param tag
	 */
	protected void showFragment(int layout, BaseFragment f, String tag) {
		if (f != null) {
			if (!f.isAdded()) {
				ensureTransaction();
				f.updateNet();
				mFragmentTransaction.add(layout, f, tag);
			} else {
				ensureTransaction();
				f.updateNet();
				mFragmentTransaction.show(f);
			}
		}
	}

	/**
	 * 进行传值
	 * 
	 * @param layout
	 * @param f
	 * @param tag
	 * @param bundle
	 */
	protected void showFragment(int layout, BaseFragment f, String tag,
			Bundle bundle) {
		if (f != null) {
			if (!f.isAdded()) {
				ensureTransaction();
				f.updateNet(bundle);
				mFragmentTransaction.add(layout, f, tag);
			} else {
				ensureTransaction();
				f.updateNet(bundle);
				mFragmentTransaction.show(f);
			}
		}
	}

	protected void hideFragment(Fragment f) {
		if (f != null) {
			if (f.isAdded()) {
				ensureTransaction();
				mFragmentTransaction.hide(f);
			}
		}
	}

	protected void commitTransactions() {
		if (mFragmentTransaction != null && !mFragmentTransaction.isEmpty()) {
			mFragmentTransaction.commit();
			mFragmentTransaction = null;
		}
	}

	/**
	 * 采用attach和detach来实现fragment的切换，每一次都会进入onCreateView
	 * 
	 * @param tag
	 */
	protected void switchFragmenCreate(String tag) {
		if (TextUtils.equals(mCurrentFragmentTag, tag))
			return;
		if (mCurrentFragmentTag != null)
			detachFragment(getFragment(mCurrentFragmentTag));
		attachFragment(R.id.container, getFragment(tag), tag);
		mCurrentFragmentTag = tag;
		commitTransactions();
	}

	/**
	 * 采用show和hide来实现fragment的切换，只有第一次都会进入onCreateView
	 * 
	 * @param tag
	 */
	protected void switchFragmen(String tag) {
		if (TextUtils.equals(mCurrentFragmentTag, tag))
			return;
		if (mCurrentFragmentTag != null)
			hideFragment(getFragment(mCurrentFragmentTag));
		showFragment(R.id.container, getFragment(tag), tag);
		mCurrentFragmentTag = tag;
		commitTransactions();
	}

	protected void switchFragmen(String tag, Bundle bundle) {
		if (TextUtils.equals(mCurrentFragmentTag, tag))
			return;
		if (mCurrentFragmentTag != null) {
			BaseFragment preFragment = getFragment(mCurrentFragmentTag);
			preFragment.onPause();
			hideFragment(preFragment);
		}
		showFragment(R.id.container, getFragment(tag), tag, bundle);
		mCurrentFragmentTag = tag;
		commitTransactions();
	}

}
