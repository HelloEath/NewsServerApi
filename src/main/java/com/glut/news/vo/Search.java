package com.glut.news.vo;

public class Search {

	private int Article_Id;//	integer	非空	主键	文章Id
    private String Article_Title;//	varchar(255)	非空		文章标题
    private String Article_Abstract;//	Varchar(255)	非空		文章摘要
    public String Article_Content;//	clob	非空		文章内容
    private String Article_Url	;//Varchar(255)	非空		文章详情页Url
    private String Article_Type;//	varchar(255)	非空		文章类型
    private int Article_Size;//	integer	非空		文章大小
    private String Article_Time;//	date	非空		文章发表时间
	 private String Article_Author_name;//	varchar(255)	非空		文章发表者昵称
	 private String Article_Author_logo;//作者头像
	 private String Article_Author_desc;//作者简介
	
	private int Artilce_Comments;//	integer	非空		文章评论数
	 private int Article_Looks;//	integer	非空		文章查看次数
	 private int Article_Stars;//	integer	非空		文章收藏数量
	/* private String Article_IsTop;//	varchar(255)	非空		文章是否置顶
	 private String Article_IsNew;//	varchar(255)	非空		文章是否原创
	 private String Article_IsOrigina;//	varchar(255)	非空		文章是否可转载
*/	 private int Article_Likes	;//integer	非空		文章点赞次数
	 private String Article_Image;
	private String Article_KeyWords;
	
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
	public int getArticle_Id() {
		return Article_Id;
	}
	public void setArticle_Id(int article_Id) {
		Article_Id = article_Id;
	}
	public String getArticle_Title() {
		return Article_Title;
	}
	public void setArticle_Title(String article_Title) {
		Article_Title = article_Title;
	}
	public String getArticle_Abstract() {
		return Article_Abstract;
	}
	public void setArticle_Abstract(String article_Abstract) {
		Article_Abstract = article_Abstract;
	}
	public String getArticle_Content() {
		return Article_Content;
	}
	public void setArticle_Content(String article_Content) {
		Article_Content = article_Content;
	}
	public String getArticle_Url() {
		return Article_Url;
	}
	public void setArticle_Url(String article_Url) {
		Article_Url = article_Url;
	}
	public String getArticle_Type() {
		return Article_Type;
	}
	public void setArticle_Type(String article_Type) {
		Article_Type = article_Type;
	}
	public int getArticle_Size() {
		return Article_Size;
	}
	public void setArticle_Size(int article_Size) {
		Article_Size = article_Size;
	}
	public String getArticle_Time() {
		return Article_Time;
	}
	public void setArticle_Time(String article_Time) {
		Article_Time = article_Time;
	}
	public String getArticle_Author_name() {
		return Article_Author_name;
	}
	public void setArticle_Author_name(String article_Author_name) {
		Article_Author_name = article_Author_name;
	}
	public String getArticle_Author_logo() {
		return Article_Author_logo;
	}
	public void setArticle_Author_logo(String article_Author_logo) {
		Article_Author_logo = article_Author_logo;
	}
	public String getArticle_Author_desc() {
		return Article_Author_desc;
	}
	public void setArticle_Author_desc(String article_Author_desc) {
		Article_Author_desc = article_Author_desc;
	}
	public int getArtilce_Comments() {
		return Artilce_Comments;
	}
	public void setArtilce_Comments(int artilce_Comments) {
		Artilce_Comments = artilce_Comments;
	}
	public int getArticle_Looks() {
		return Article_Looks;
	}
	public void setArticle_Looks(int article_Looks) {
		Article_Looks = article_Looks;
	}
	public int getArticle_Stars() {
		return Article_Stars;
	}
	public void setArticle_Stars(int article_Stars) {
		Article_Stars = article_Stars;
	}
	public int getArticle_Likes() {
		return Article_Likes;
	}
	public void setArticle_Likes(int article_Likes) {
		Article_Likes = article_Likes;
	}
	public String getArticle_Image() {
		return Article_Image;
	}
	public void setArticle_Image(String article_Image) {
		Article_Image = article_Image;
	}
	public String getArticle_KeyWords() {
		return Article_KeyWords;
	}
	public void setArticle_KeyWords(String article_KeyWords) {
		Article_KeyWords = article_KeyWords;
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
	public String getVideo_time() {
		return Video_time;
	}
	public void setVideo_time(String video_time) {
		Video_time = video_time;
	}
	public String getVideo_From() {
		return Video_From;
	}
	public void setVideo_From(String video_From) {
		Video_From = video_From;
	}
	public String getVideo_PutTime() {
		return Video_PutTime;
	}
	public void setVideo_PutTime(String video_PutTime) {
		Video_PutTime = video_PutTime;
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
	public String getVideo_KeyWord() {
		return video_KeyWord;
	}
	public void setVideo_KeyWord(String video_KeyWord) {
		this.video_KeyWord = video_KeyWord;
	}
	
}
