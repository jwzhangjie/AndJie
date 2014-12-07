package com.jwzhangjie.andbase.doc;

import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import com.jwzhangjie.andbase.R;
import com.jwzhangjie.andbase.popup.AddPicturePopup;
import com.jwzhangjie.andbase.ui.base.BaseActivity;
import com.jwzhangjie.andbase.util.FileUtils;
import com.jwzhangjie.andbase.util.JieContant;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * title: SelectPicUsed.java
 * @author jwzhangjie
 * Date: 2014-12-7 下午10:00:59
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:演示添加本地图片和摄像头拍照
 */
@ContentView(R.layout.activity_select_picture)
public class SelectPicUsed extends BaseActivity {

	@ViewInject(R.id.select_pic)
	private ImageView selectPic;
	
	ImageLoader imageLoader = ImageLoader.getInstance();

	@OnClick(R.id.select_pic)
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
		case R.id.select_pic:
			AddPicturePopup  mPicturePopup = new AddPicturePopup(this);
			mPicturePopup.initPopView(view);
			break;

		default:
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (resultCode == JieContant.RESULT_OK) {
			switch (requestCode) {
			case JieContant.POP_ADD_PICTURE_FROM_CAMERA:
				// 这个拍照也是一张
				File f = new File(Environment.getExternalStorageDirectory()
						+ "/localTempImgDir/pickImg.jpg");
				try {
					Uri u = Uri.parse(android.provider.MediaStore.Images.Media
							.insertImage(getContentResolver(),
									f.getAbsolutePath(), null, null));
					if (u != null) {
						String filename = FileUtils.getPath(u, this);
						imageLoader.displayImage("file:///"+filename, selectPic);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case JieContant.POP_ADD_PICTURE_FROM_PHOTO:
				// 这个是最近照片，选择一张
				Uri uri = intent.getData();
				if (uri != null) {
					String filename = FileUtils.getPath(uri, this);
					imageLoader.displayImage("file:///"+filename, selectPic);
				}
				break;
			default:
				break;
			}
		}
	}
}
