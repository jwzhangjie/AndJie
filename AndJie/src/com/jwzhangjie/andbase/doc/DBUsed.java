package com.jwzhangjie.andbase.doc;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.jwzhangjie.andbase.doc.db.DBUtils;
import com.jwzhangjie.andbase.doc.db.Users;


/**
 * title: DBUsed.java
 * @author jwzhangjie
 * Date: 2014年12月8日 下午12:48:21
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:关于ormlite的具体使用实例
 */
public class DBUsed {

	private DBUtils mDbUtils;
	public DBUsed(Context context){
		mDbUtils = new DBUtils(context);
	}
	
	public void createUsers(){
		List<Users> users = new ArrayList<Users>();
		Users user1 = new Users();
		user1.setEmail("jwzhangjie@163.com");
		user1.setName("与狼共舞");
		user1.setPwd("123456");
		users.add(user1);
		Users user2 = new Users();
		user2.setEmail("77777@qq.com");
		user2.setName("海飘");
		user2.setPwd("123456");
		users.add(user2);
		mDbUtils.moreUserCreate(users);
	}
}
