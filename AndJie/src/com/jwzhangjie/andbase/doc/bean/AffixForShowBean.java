package com.jwzhangjie.andbase.doc.bean;

import com.jwzhangjie.andbase.interfaces.IParcelable;

import android.os.Parcel;

/**
 * title: AffixForShowBean.java
 * @author jwzhangjie
 * Date: 2014年12月9日 上午11:10:09
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:图片的实体类
 */
public class AffixForShowBean implements IParcelable {

	@Override
	public int describeContents() {
		return 0;
	}

	public AffixForShowBean() {

	}

	private AffixForShowBean(Parcel source) {
		readFromParcel(source);
	}
	
	private int id;
	private String sysNumber;
	//图片存放文件夹 - 原图
	private String affixFolder;
	//图片名称（系统生成） - 上传的原图
	private String affixName;
	//图片名称 - 根据上传的图片生成的小图片，用于列表显示时使用
	private String affixNameSmall;
	//图片名称（原名） - 上传的原图
	private String affixOldName;

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(sysNumber);
		dest.writeString(affixFolder);
		dest.writeString(affixName);
		dest.writeString(affixNameSmall);
		dest.writeString(affixOldName);
	}

	@Override
	public void readFromParcel(Parcel source) {
		id = source.readInt();
		sysNumber = source.readString();
		affixFolder = source.readString();
		affixName = source.readString();
		affixNameSmall = source.readString();
		affixOldName = source.readString();
	}

	public static Creator<AffixForShowBean> CREATOR = new Creator<AffixForShowBean>() {

		@Override
		public AffixForShowBean createFromParcel(Parcel source) {
			return new AffixForShowBean(source);
		}

		@Override
		public AffixForShowBean[] newArray(int size) {
			return new AffixForShowBean[size];
		}
	};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSysNumber() {
		return sysNumber;
	}

	public void setSysNumber(String sysNumber) {
		this.sysNumber = sysNumber;
	}

	public String getAffixFolder() {
		return affixFolder;
	}

	public void setAffixFolder(String affixFolder) {
		this.affixFolder = affixFolder;
	}

	public String getAffixName() {
		return affixName;
	}

	public void setAffixName(String affixName) {
		this.affixName = affixName;
	}

	public String getAffixNameSmall() {
		return affixNameSmall;
	}

	public void setAffixNameSmall(String affixNameSmall) {
		this.affixNameSmall = affixNameSmall;
	}

	public String getAffixOldName() {
		return affixOldName;
	}

	public void setAffixOldName(String affixOldName) {
		this.affixOldName = affixOldName;
	}
	
}
