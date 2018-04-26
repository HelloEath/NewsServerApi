package com.glut.news.vo;

public class History {

	private int History_Id;
	private int History_Article;
	private int History_Persion;
	private String History_Time;
	private int History_Type;//2：视频，1：图文
	private Article article;
	private Video video;
	private String Content_type;
	
	public String getContent_type() {
		return Content_type;
	}
	public void setContent_type(String content_type) {
		Content_type = content_type;
	}
	public int getHistory_Id() {
		return History_Id;
	}
	public void setHistory_Id(int history_Id) {
		History_Id = history_Id;
	}
	public int getHistory_Article() {
		return History_Article;
	}
	public void setHistory_Article(int history_Article) {
		History_Article = history_Article;
	}
	public int getHistory_Persion() {
		return History_Persion;
	}
	public void setHistory_Persion(int history_Persion) {
		History_Persion = history_Persion;
	}
	public String getHistory_Time() {
		return History_Time;
	}
	public void setHistory_Time(String history_Time) {
		History_Time = history_Time;
	}
	public int getHistory_Type() {
		return History_Type;
	}
	public void setHistory_Type(int history_Type) {
		History_Type = history_Type;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	
	
}
