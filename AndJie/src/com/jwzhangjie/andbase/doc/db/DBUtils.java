package com.jwzhangjie.andbase.doc.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

/**
 * title: DBUtils.java
 * @author jwzhangjie
 * Date: 2014年12月8日 下午12:44:52
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:数据库操作类
 */
public class DBUtils {

	public static Dao<Users, Integer> usersDao = null;
	public static Dao<Emails, Integer> emailDao = null;

	public DBUtils(Context context) {
		if (usersDao == null || emailDao == null) {
			DBHelper dbHelper = new DBHelper(context);
			try {
				if (usersDao == null) {
					usersDao = dbHelper.getDao(Users.class);
				}
				if (emailDao == null) {
					emailDao = dbHelper.getDao(Emails.class);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 插入一个用户
	 */
	public void oneUserCreate(Users user) {
		try {
			usersDao.createOrUpdate(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 连续插入多个用户
	 */

	public void moreUserCreate(List<Users> users) {
		try {
			for (Users user : users) {
				usersDao.createOrUpdate(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有的用户
	 */
	public List<Users> getAllUsers() {
		List<Users> users = new ArrayList<Users>();
		try {
			users = usersDao.queryForAll();
			users.remove(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	/**
	 * 插入一封邮件
	 */
	public void oneEmailCreate(Emails email) {
		try {
			emailDao.create(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入多封邮件
	 */
	public void moreEmailCreate(List<Emails> emails) {
		try {
			for (Emails email : emails) {
				emailDao.create(email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新一封邮件状态
	 */
	public void updateEmail(Emails email) {
		try {
			emailDao.update(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除一个邮件
	 */
	public void deleOneEmail(Emails email) {
		try {
			emailDao.update(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询对应邮箱的用户
	 */
	public Users getUsersFromEmail(String email) {
		Users users = new Users();
		try {
			List<Users> uList = usersDao.queryForEq("email", email);
			if (uList != null && uList.size() > 0) {
				users = uList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	/**
	 * 查询所有收件人邮件
	 */
	public List<Emails> getAllInBoxEmails(String receiverId) {
		List<Emails> emails = new ArrayList<Emails>();
		try {
			emails = emailDao.queryBuilder().orderBy("id", false).where()
					.eq("receiverId", receiverId)
					.and().eq("state_receiver", "0").query();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emails;
	}
	
	/**
	 * 删除所有的收件箱
	 */
	public void deleteAllInBoxEmails(int type, String id){
		List<Emails> emails = null;
		try {
			switch (type) {
			case 1://收件箱
				emails = getAllInBoxEmails(id);
				for (Emails email : emails) {
					email.setState_receiver("1");
					emailDao.update(email);
				}
				break;
			case 2://发件箱
				emails = getAllSendEmals(id);
				for (Emails email : emails) {
					email.setState_send("1");
					emailDao.update(email);
				}
				break;
			case 3://删除箱
				emails = getAllDeleteEmals(id);
				for (Emails email : emails) {
					email.setState_receiver("-1");
					email.setState_send("-1");
					emailDao.update(email);
				}
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询未读收件邮件个数
	 */
	public int getUnReadInBoxEmail(String receiverId) {
		int count = 0;
		try {
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("receiverId", receiverId);
			params.put("state_receiver", 0);// 正常
			params.put("unRead", 0);// 未读
			count = emailDao.queryForFieldValues(params).size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 查询所有发件人邮件
	 */
	public List<Emails> getAllSendEmals(String sendId) {
		List<Emails> emails = new ArrayList<Emails>();
		try {
			emails = emailDao.queryBuilder().orderBy("id", false).where()
					.eq("sendId", sendId)
					.and().eq("state_send", "0").query();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emails;
	}

	/**
	 * 查询所有已删除邮件
	 * 
	 * @email 收件人和发件人
	 */
	public List<Emails> getAllDeleteEmals(String email) {
		List<Emails> emails = new ArrayList<Emails>();
		List<Emails> emails_send = new ArrayList<Emails>();
		try {
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("sendId", email);
			params.put("state_send", 1);
			emails_send = emailDao.queryForFieldValues(params);
			params.remove("sendId");
			params.remove("state_send");
			params.put("receiverId", email);
			params.put("state_receiver", 1);
			emails = emailDao.queryForFieldValues(params);
			emails.addAll(emails_send);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emails;
	}
}
