package com.glut.news.vo;

import java.sql.Date;

import com.google.gson.annotations.SerializedName;

public class UserInfo {
	private int User_Id	;//integer	非空	主键	id
	
	private String User_NickName;//	varchar(255)	非空		用户昵称(登录名)
	
	private String User_Password;//	varchar(255)	非空		密码
	private String User_Type;//	varchar(255)	非空		用户类型
	private String User_Sex	;//Varchar(255)	非空		性别
	private String User_Picture;//	varchar(255)	非空		头像地址
	
	private String User_PhoneNum;//	varchar(255)	非空	唯一性	手机号码
	
	private String User_Email	;//varchar(255)	非空	唯一性	邮箱
	private String User_QQ;//	varchar(255)	非空	唯一性	qq号码
	private String User_WeChat;//	varchar(255)	非空	唯一性	微信
	private String User_Describe;//	varchar(255)	非空		个人说明
	private String User_District;//	Varchar(255)	非空		所在地
	private String User_Birthday	;//date	非空		出生日期
	private int User_Comments;//	integer	非空		评论文章数
	private int User_Historys;//	integer	非空		历史记录数
	private int User_Articles;//	integer	非空		发表文章数
	
	private int User_Videos	;//integer	非空		发表视频数
	private int User_Stars;//	integer	非空		收藏文章数
	private int User_Notifys;//	ingeter	非空		通知信息数
	private Date User_RegisterTime;//	date	非空		注册时间
	private Date User_LastLoginTime;//	date	非空		最后登录时间
	private String User_Interest;
	
	
	public String getUser_Interest() {
		return User_Interest;
	}
	public void setUser_Interest(String user_Interest) {
		User_Interest = user_Interest;
	}
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}
	public int getUser_Id() {
		return User_Id;
	}
	public void setUser_Id(int user_Id) {
		User_Id = user_Id;
	}
	public String getUser_NickName() {
		return User_NickName;
	}
	public void setUser_NickName(String user_NickName) {
		User_NickName = user_NickName;
	}
	public String getUser_Password() {
		return User_Password;
	}
	public void setUser_Password(String user_Password) {
		User_Password = user_Password;
	}
	public String getUser_Type() {
		return User_Type;
	}
	public void setUser_Type(String user_Type) {
		User_Type = user_Type;
	}
	public String getUser_Sex() {
		return User_Sex;
	}
	public void setUser_Sex(String user_Sex) {
		User_Sex = user_Sex;
	}
	public String getUser_Picture() {
		return User_Picture;
	}
	public void setUser_Picture(String user_Picture) {
		User_Picture = user_Picture;
	}
	public String getUser_PhoneNum() {
		return User_PhoneNum;
	}
	public void setUser_PhoneNum(String user_PhoneNum) {
		User_PhoneNum = user_PhoneNum;
	}
	public String getUser_Email() {
		return User_Email;
	}
	public void setUser_Email(String user_Email) {
		User_Email = user_Email;
	}
	public String getUser_QQ() {
		return User_QQ;
	}
	public void setUser_QQ(String user_QQ) {
		User_QQ = user_QQ;
	}
	public String getUser_WeChat() {
		return User_WeChat;
	}
	public void setUser_WeChat(String user_WeChat) {
		User_WeChat = user_WeChat;
	}
	public String getUser_Describe() {
		return User_Describe;
	}
	public void setUser_Describe(String user_Describe) {
		User_Describe = user_Describe;
	}
	public String getUser_District() {
		return User_District;
	}
	public void setUser_District(String user_District) {
		User_District = user_District;
	}
	public String getUser_Birthday() {
		return User_Birthday;
	}
	public void setUser_Birthday(String user_Birthday) {
		User_Birthday = user_Birthday;
	}
	public int getUser_Comments() {
		return User_Comments;
	}
	public void setUser_Comments(int user_Comments) {
		User_Comments = user_Comments;
	}
	public int getUser_Historys() {
		return User_Historys;
	}
	public void setUser_Historys(int user_Historys) {
		User_Historys = user_Historys;
	}
	public int getUser_Articles() {
		return User_Articles;
	}
	public void setUser_Articles(int user_Articles) {
		User_Articles = user_Articles;
	}
	public int getUser_Videos() {
		return User_Videos;
	}
	public void setUser_Videos(int user_Videos) {
		User_Videos = user_Videos;
	}
	public int getUser_Stars() {
		return User_Stars;
	}
	public void setUser_Stars(int user_Stars) {
		User_Stars = user_Stars;
	}
	public int getUser_Notifys() {
		return User_Notifys;
	}
	public void setUser_Notifys(int user_Notifys) {
		User_Notifys = user_Notifys;
	}
	public Date getUser_RegisterTime() {
		return User_RegisterTime;
	}
	public void setUser_RegisterTime(Date user_RegisterTime) {
		User_RegisterTime = user_RegisterTime;
	}
	public Date getUser_LastLoginTime() {
		return User_LastLoginTime;
	}
	public void setUser_LastLoginTime(Date user_LastLoginTime) {
		User_LastLoginTime = user_LastLoginTime;
	}
	@Override
	public String toString() {
		return "User [User_Id=" + User_Id + ", User_NickName=" + User_NickName + ", User_Password=" + User_Password
				+ ", User_Type=" + User_Type + ", User_Sex=" + User_Sex + ", User_Picture=" + User_Picture
				+ ", User_PhoneNum=" + User_PhoneNum + ", User_Email=" + User_Email + ", User_QQ=" + User_QQ
				+ ", User_WeChat=" + User_WeChat + ", User_Describe=" + User_Describe + ", User_District="
				+ User_District + ", User_Birthday=" + User_Birthday + ", User_Comments=" + User_Comments
				+ ", User_Historys=" + User_Historys + ", User_Articles=" + User_Articles + ", User_Videos="
				+ User_Videos + ", User_Stars=" + User_Stars + ", User_Notifys=" + User_Notifys + ", User_RegisterTime="
				+ User_RegisterTime + ", User_LastLoginTime=" + User_LastLoginTime + "]";
	}
	

}
