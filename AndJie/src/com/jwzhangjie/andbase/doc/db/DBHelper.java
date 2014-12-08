package com.jwzhangjie.andbase.doc.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


/**
 * title: DBHelper.java
 * @author jwzhangjie
 * Date: 2014年12月8日 下午12:44:37
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:数据库的一个帮助类
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {
	
	private static final String DATABASE_NAME = "jwzhangjie.db";
	private static final int DATABASE_VERSION = 1;

	public DBHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);  
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Users.class);
			TableUtils.createTable(connectionSource, Emails.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVer,
			int newVer) {
		try {
			TableUtils.dropTable(connectionSource, Users.class, true);
			TableUtils.dropTable(connectionSource, Emails.class, true);
			 onCreate(sqLiteDatabase, connectionSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
