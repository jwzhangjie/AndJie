package com.jwzhangjie.andbase.doc.adapter;

import java.util.List;

import com.jwzhangjie.andbase.R;
import com.jwzhangjie.andbase.doc.bean.GsonBean;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * title: AccountAdapter.java
 * @author jwzhangjie
 * Date: 2014-12-6 下午4:50:10
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:RecyclerView的适配器
 */
public class AccountAdapter extends
		RecyclerView.Adapter<AccountAdapter.ViewHolder> {

	private List<GsonBean> listDatas;

	public void setContent(List<GsonBean> listDatas) {
		this.listDatas = listDatas;
		notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		return listDatas == null ? 0 : listDatas.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		GsonBean bean = listDatas.get(position);
		holder.accountName.setText(bean.getUser_name());
		holder.accountPwd.setText(bean.getUser_pwd());
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viweGroup, int arg1) {
		View view = LayoutInflater.from(viweGroup.getContext()).inflate(
				R.layout.item_account_adapter_item, null);
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		private ImageView accountImg;
		private TextView accountName;
		private TextView accountPwd;

		public ViewHolder(View itemView) {
			super(itemView);
			accountImg = (ImageView) itemView.findViewById(R.id.accountImgId);
			accountName = (TextView) itemView.findViewById(R.id.account_name);
			accountPwd = (TextView) itemView.findViewById(R.id.account_pwd);
		}
	}
}
