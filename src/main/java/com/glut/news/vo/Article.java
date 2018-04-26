package com.glut.news.vo;

import java.sql.Date;

public class Article {

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
	 private String Article_IsEnable;//	varchar(255)	非空		文章是否有效
	 
	private UserInfo userInfo;
	private Comments comment;
	private String Article_Image;
	private String Article_KeyWords;
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
		public UserInfo getUserInfo() {
			return userInfo;
		}
		public void setUserInfo(UserInfo userInfo) {
			this.userInfo = userInfo;
		}
		public Comments getComment() {
			return comment;
		}
		public void setComment(Comments comment) {
			this.comment = comment;
		}
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
	/*public String getArticle_IsTop() {
		return Article_IsTop;
	}
	public void setArticle_IsTop(String article_IsTop) {
		Article_IsTop = article_IsTop;
	}
	public String getArticle_IsNew() {
		return Article_IsNew;
	}
	public void setArticle_IsNew(String article_IsNew) {
		Article_IsNew = article_IsNew;
	}
	public String getArticle_IsOrigina() {
		return Article_IsOrigina;
	}
	public void setArticle_IsOrigina(String article_IsOrigina) {
		Article_IsOrigina = article_IsOrigina;
	}*/
	public int getArticle_Likes() {
		return Article_Likes;
	}
	public void setArticle_Likes(int article_Likes) {
		Article_Likes = article_Likes;
	}
	public String getArticle_IsEnable() {
		return Article_IsEnable;
	}
	public void setArticle_IsEnable(String article_IsEnable) {
		Article_IsEnable = article_IsEnable;
	}
	@Override
	public String toString() {
		return "Article [Article_Id=" + Article_Id + ", Article_Title=" + Article_Title + ", Article_Abstract="
				+ Article_Abstract + ", Article_Content=" + Article_Content + ", Article_Url=" + Article_Url
				+ ", Article_Type=" + Article_Type + ", Article_Size=" + Article_Size + ", Article_Time=" + Article_Time
				+ ", Article_Author_name=" + Article_Author_name + ", Article_Author_logo=" + Article_Author_logo
				+ ", Article_Author_desc=" + Article_Author_desc + ", Artilce_Comments=" + Artilce_Comments
				+ ", Article_Looks=" + Article_Looks + ", Article_Stars=" + Article_Stars + ", Article_Likes="
				+ Article_Likes + ", Article_IsEnable=" + Article_IsEnable + ", userInfo=" + userInfo + ", comment="
				+ comment + ", Article_Image=" + Article_Image + ", Article_KeyWords=" + Article_KeyWords + "]";
	}
	
	

	 
}
