package com.glut.news.vo;

import java.sql.Date;

public class Video {

	private int Video_Id;// integer 非空 主键 视频Id
	private String Video_Title;// varchar(255) 非空 视频标题
	private String Video_Abstract;// Varchar(255) 非空 视频摘要
	private String Video_Image;// Varchar(255) 非空 视频封面
	private String Video_Player;// Varchar(255) 非空 唯一 视频播放Url
	private String Video_Download;// Varchar(255) 唯一 视频下载Url
	private String Video_Type;// varchar(255) 非空 视频类型
	private int Video_Size;// integer 非空 视频大小
	private String Video_time;//
	
	private String  Video_From;
	private String  Video_PutTime;// date 非空 视频发表时间
	private int Video_Author;// Integer 非空 视频发表者ID
	private String Video_Author_Name;// varchar(255) 非空 视频作者名字
	private String Video_Author_Logo;// varchar(255) 非空 视频作者头像
	private int Video_Comments;// integer 非空 视频评论数
	private int Video_Players;// integer 非空 视频播放数
	private int Video_Stars;// integer 非空 视频收藏数
	private int Video_Likes;// integer 非空 视频点赞次
/*	private String Video_IsNew;// varchar(255) 非空 视频是否原创
	private String Video_IsOrigina;// varchar(255) 非空 视频是否可转载
	private String Video_IsEnable;// varchar(255) 非空 视频是否有效
*/	
	private String video_KeyWord;
	
	public String getVideo_KeyWord() {
		return video_KeyWord;
	}
	public void setVideo_KeyWord(String video_KeyWord) {
		this.video_KeyWord = video_KeyWord;
	}
	public String getVideo_From() {
		return Video_From;
	}
	public void setVideo_From(String video_From) {
		Video_From = video_From;
	}
	public String getVideo_time() {
		return Video_time;
	}
	public void setVideo_time(String video_time) {
		Video_time = video_time;
	}
	public int getVideo_Id() {
		return Video_Id;
	}
	public void setVideo_Id(int video_Id) {
		Video_Id = video_Id;
	}
	public String getVideo_Title() {
		return Video_Title;
	}
	public void setVideo_Title(String video_Title) {
		Video_Title = video_Title;
	}
	public String getVideo_Abstract() {
		return Video_Abstract;
	}
	public void setVideo_Abstract(String video_Abstract) {
		Video_Abstract = video_Abstract;
	}
	public String getVideo_Image() {
		return Video_Image;
	}
	public void setVideo_Image(String video_Image) {
		Video_Image = video_Image;
	}
	public String getVideo_Player() {
		return Video_Player;
	}
	public void setVideo_Player(String video_Player) {
		Video_Player = video_Player;
	}
	public String getVideo_Download() {
		return Video_Download;
	}
	public void setVideo_Download(String video_Download) {
		Video_Download = video_Download;
	}
	public String getVideo_Type() {
		return Video_Type;
	}
	public void setVideo_Type(String video_Type) {
		Video_Type = video_Type;
	}
	public int getVideo_Size() {
		return Video_Size;
	}
	public void setVideo_Size(int video_Size) {
		Video_Size = video_Size;
	}
	public String getVideo_PutTime() {
		return Video_PutTime;
	}
	public void setVideo_PutTime(String video_Time) {
		Video_PutTime = video_Time;
	}
	public int getVideo_Author() {
		return Video_Author;
	}
	public void setVideo_Author(int video_Author) {
		Video_Author = video_Author;
	}
	public String getVideo_Author_Name() {
		return Video_Author_Name;
	}
	public void setVideo_Author_Name(String video_Author_Name) {
		Video_Author_Name = video_Author_Name;
	}
	public String getVideo_Author_Logo() {
		return Video_Author_Logo;
	}
	public void setVideo_Author_Logo(String video_Author_Logo) {
		Video_Author_Logo = video_Author_Logo;
	}
	public int getVideo_Comments() {
		return Video_Comments;
	}
	public void setVideo_Comments(int video_Comments) {
		Video_Comments = video_Comments;
	}
	public int getVideo_Players() {
		return Video_Players;
	}
	public void setVideo_Players(int video_Players) {
		Video_Players = video_Players;
	}
	public int getVideo_Stars() {
		return Video_Stars;
	}
	public void setVideo_Stars(int video_Stars) {
		Video_Stars = video_Stars;
	}
	public int getVideo_Likes() {
		return Video_Likes;
	}
	public void setVideo_Likes(int video_Likes) {
		Video_Likes = video_Likes;
	}
	/*public String getVideo_IsNew() {
		return Video_IsNew;
	}
	public void setVideo_IsNew(String video_IsNew) {
		Video_IsNew = video_IsNew;
	}
	public String getVideo_IsOrigina() {
		return Video_IsOrigina;
	}
	public void setVideo_IsOrigina(String video_IsOrigina) {
		Video_IsOrigina = video_IsOrigina;
	}
	public String getVideo_IsEnable() {
		return Video_IsEnable;
	}
	public void setVideo_IsEnable(String video_IsEnable) {
		Video_IsEnable = video_IsEnable;
	}*/
	@Override
	public String toString() {
		return "Video [Video_Id=" + Video_Id + ", Video_Title=" + Video_Title + ", Video_Abstract=" + Video_Abstract
				+ ", Video_Image=" + Video_Image + ", Video_Player=" + Video_Player + ", Video_Download="
				+ Video_Download + ", Video_Type=" + Video_Type + ", Video_Size=" + Video_Size + ", Video_time="
				+ Video_time + ", Video_From=" + Video_From + ", Video_PutTime=" + Video_PutTime + ", Video_Author="
				+ Video_Author + ", Video_Author_Name=" + Video_Author_Name + ", Video_Author_Logo=" + Video_Author_Logo
				+ ", Video_Comments=" + Video_Comments + ", Video_Players=" + Video_Players + ", Video_Stars="
				+ Video_Stars + ", Video_Likes=" + Video_Likes + ", video_KeyWord=" + video_KeyWord + "]";
	}
	
	
}
