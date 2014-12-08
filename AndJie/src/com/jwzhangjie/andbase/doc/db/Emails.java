package com.jwzhangjie.andbase.doc.db;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;


/**
 * title: Emails.java
 * @author jwzhangjie
 * Date: 2014年12月8日 下午12:42:40
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:使用ormlite创建表
 */
public class Emails implements Serializable{

	private static final long serialVersionUID = 1L;

	public Emails(){
		
	}
	
	@DatabaseField(generatedId = true)
	private int id;
	//发件人邮箱
	@DatabaseField
	private String sendId;
	//收件人邮箱
	@DatabaseField
	private String receiverId;
	//邮件标题
	@DatabaseField
	private String title;
	//邮件内容
	@DatabaseField
	private String content;
	//转账金额
	@DatabaseField
	private double money_num;
	//转账详情
	@DatabaseField
	private String money_detail;
	//创建日期
	@DatabaseField
	private String createTime;
	//转/收账
	@DatabaseField
	private int money_type;
	//邮箱状态  0:正常 1:删除 发件人删除
	@DatabaseField
	private String state_send;
	//邮箱状态  0:正常 1:删除 收件人删除
	@DatabaseField
	private String state_receiver;
	//是否读取 0:未读 1：已读
	@DatabaseField
	private int unRead;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public double getMoney_num() {
		return money_num;
	}
	public void setMoney_num(double money_num) {
		this.money_num = money_num;
	}
	public String getMoney_detail() {
		return money_detail;
	}
	public void setMoney_detail(String money_detail) {
		this.money_detail = money_detail;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getMoney_type() {
		return money_type;
	}
	public void setMoney_type(int money_type) {
		this.money_type = money_type;
	}
	
	public String getState_send() {
		return state_send;
	}
	public void setState_send(String state_send) {
		this.state_send = state_send;
	}
	public String getState_receiver() {
		return state_receiver;
	}
	public void setState_receiver(String state_receiver) {
		this.state_receiver = state_receiver;
	}
	public int getUnRead() {
		return unRead;
	}
	public void setUnRead(int unRead) {
		this.unRead = unRead;
	}
	
}
