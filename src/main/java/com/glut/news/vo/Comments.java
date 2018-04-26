package com.glut.news.vo;

import java.sql.Date;

import org.omg.CORBA.PRIVATE_MEMBER;

public class Comments {

	private int Comment_Id;// integer 非空 主键 评论id
	private String Comment_Content;// varchar(255) 非空 评论内容
	private int Comment_Author;// integer 非空 评论者ID
	private String Comment_Time;// date 非空 评论时间
	private int Comment_Article;// integer 非空 评论文章ID
	private String Comment_Enable;// varchar(255) 非空 评论是否有效
	private String Author_name;
	private String Author_logo;
	private UserInfo userInfo;
	private int Comment_Likes;
	
	
	public int getLikes() {
		return Comment_Likes;
	}
	public void setLikes(int likes) {
		this.Comment_Likes = likes;
	}
	public String getAuthor_name() {
		return Author_name;
	}
	public void setAuthor_name(String author_name) {
		Author_name = author_name;
	}
	public String getAuthor_logo() {
		return Author_logo;
	}
	public void setAuthor_logo(String author_logo) {
		Author_logo = author_logo;
	}
	public int getComment_Id() {
		return Comment_Id;
	}
	public void setComment_Id(int comment_Id) {
		Comment_Id = comment_Id;
	}
	public String getComment_Content() {
		return Comment_Content;
	}
	public void setComment_Content(String comment_Content) {
		Comment_Content = comment_Content;
	}
	public int getComment_Author() {
		return Comment_Author;
	}
	public void setComment_Author(int comment_Author) {
		Comment_Author = comment_Author;
	}
	public String getComment_Time() {
		return Comment_Time;
	}
	public void setComment_Time(String comment_Time) {
		Comment_Time = comment_Time;
	}
	public int getComment_Article() {
		return Comment_Article;
	}
	public void setComment_Article(int comment_Article) {
		Comment_Article = comment_Article;
	}

	public String getComment_Enable() {
		return Comment_Enable;
	}
	public void setComment_Enable(String comment_Enable) {
		Comment_Enable = comment_Enable;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	@Override
	public String toString() {
		return "Comment [Comment_Id=" + Comment_Id + ", Comment_Content=" + Comment_Content + ", Comment_Author="
				+ Comment_Author + ", Comment_Time=" + Comment_Time + ", Comment_Article=" + Comment_Article
				+ ", Comment_Enable=" + Comment_Enable + ", Author_name=" + Author_name + ", Author_logo=" + Author_logo
				+ ", userInfo=" + userInfo + ", Comment_Likes=" + Comment_Likes + "]";
	}

}
