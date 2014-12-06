package com.jwzhangjie.andbase.ui.base;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * title: ADBaseAdapter.java
 * @author jwzhangjie 
 * Date: 2014-12-6-下午1:38:42 
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie} 
 * Description:自定义适配器基础类，采用泛型
 */
public abstract class ADBaseAdapter extends BaseAdapter {

	public List<?> listDatas;

	public void setData(List<?> listDatas) {
		this.listDatas = listDatas;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return listDatas == null ? 0 : listDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return listDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}

}
