package com.jwzhangjie.andbase.doc.db;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * title: Users.java
 * @author jwzhangjie
 * Date: 2014年12月8日 下午12:43:49
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:使用ormlite创建表
 */
@DatabaseTable(tableName = "users")
public class Users implements Serializable{

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(unique = true)
	private String email;
	
	@DatabaseField
	private String pwd;
	
	@DatabaseField
	private String name;
	
	@DatabaseField
	private Date createTime;
	
	public Users() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
