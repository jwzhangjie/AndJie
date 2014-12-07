package com.jwzhangjie.andbase.popup;

import java.io.File;

import com.jwzhangjie.andbase.JieApp;
import com.jwzhangjie.andbase.R;
import com.jwzhangjie.andbase.ui.base.JieBasePopup;
import com.jwzhangjie.andbase.util.JieContant;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

/**
 * title: AddPicturePopup.java
 * @author jwzhangjie
 * Date: 2014-12-7 下午9:45:15
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:作为添加图片弹出框
 */
public class AddPicturePopup extends JieBasePopup implements OnClickListener {

	@ViewInject(R.id.pop_add_pic_from_camera)
	private TextView addCamera;
	@ViewInject(R.id.pop_add_pic_from_photo)
	private TextView addPhoto;
	@ViewInject(R.id.pop_add_pic_cancel)
	private TextView addCancel;
	private Fragment fragment;
	private Activity activity;

	public AddPicturePopup(Fragment fragment) {
		super();
		this.fragment = fragment;
	}

	public AddPicturePopup(Activity activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void initPopView(View parent) {
		super.initPopView(parent);
		view = (View) JieApp.instance.getInflater().inflate(
				R.layout.item_pop_add_picture, null);
		ViewUtils.inject(this, view);
		initPopWindow(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		startPop(Gravity.BOTTOM, 0, 0);
	}

	@OnClick({ R.id.pop_add_pic_from_camera, R.id.pop_add_pic_from_photo,
			R.id.pop_add_pic_cancel })
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.pop_add_pic_from_camera:
			String status = Environment.getExternalStorageState();
			if (status.equals(Environment.MEDIA_MOUNTED)) {
				try {
					File dir = new File(
							Environment.getExternalStorageDirectory()
									+ "/localTempImgDir");
					if (!dir.exists())
						dir.mkdirs();
					File f = new File(dir, "pickImg.jpg");
					Uri u = Uri.fromFile(f);
					Intent intent1 = new Intent();
					intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					intent1.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
					intent1.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
					intent1.putExtra(MediaStore.EXTRA_OUTPUT, u);
					if (fragment != null) {
						fragment.startActivityForResult(intent1,
								JieContant.POP_ADD_PICTURE_FROM_CAMERA);
					} else if (activity != null) {
						activity.startActivityForResult(intent1,
								JieContant.POP_ADD_PICTURE_FROM_CAMERA);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mPopupWindow.dismiss();
			break;
		case R.id.pop_add_pic_from_photo:
			Intent intent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			if (fragment != null) {
				fragment.startActivityForResult(intent,
						JieContant.POP_ADD_PICTURE_FROM_PHOTO);
			} else if (activity != null) {
				activity.startActivityForResult(intent,
						JieContant.POP_ADD_PICTURE_FROM_PHOTO);
			}
			mPopupWindow.dismiss();
			break;
		case R.id.pop_add_pic_cancel:
			mPopupWindow.dismiss();
			break;
		default:
			break;
		}
	}

}
