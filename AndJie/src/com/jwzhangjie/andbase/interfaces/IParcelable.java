package com.jwzhangjie.andbase.interfaces;

import android.os.Parcel;
import android.os.Parcelable;

public interface IParcelable extends Parcelable {
	void readFromParcel(Parcel source);
}
