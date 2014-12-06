package com.jwzhangjie.andbase.doc.bean;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;

import com.jwzhangjie.andbase.interfaces.IParcelable;

/**
 * title: GsonBean.java
 * @author jwzhangjie
 * Date: 2014-12-6 下午3:52:52
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:这个类来测试Gson解析的，同时推荐使用Parcelable而不是Serializable
 */
public class GsonBean implements IParcelable{

	@Override
	public int describeContents() {
		return 0;
	}
	
	public GsonBean(){
		
	}
	
	public GsonBean(Parcel source){
		readFromParcel(source);
	}
	
	//用户登录账号
	private String user_name;
	//用户登录密码
	private String user_pwd;
	//其他信息,一定要先初始化
	private List<String> other = new ArrayList<String>();

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(user_name);
		dest.writeString(user_pwd);
		dest.writeList(other);
	}

	@Override
	public void readFromParcel(Parcel source) {
		user_name = source.readString();
		user_pwd = source.readString();
		source.readList(other, String.class.getClassLoader());
	}
	
	public static Creator<GsonBean> CREATOR = new Creator<GsonBean>() {

		@Override
		public GsonBean createFromParcel(Parcel source) {
			return new GsonBean(source);
		}

		@Override
		public GsonBean[] newArray(int size) {
			return new GsonBean[size];
		}
		
	};

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public List<String> getOther() {
		return other;
	}

	public void setOther(List<String> other) {
		this.other = other;
	}

	
}
