package com.glut.news.commons;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

import javax.annotation.Resource;
import javax.sound.sampled.DataLine;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement.ElseIf;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.glut.news.mapper.ArticleMapper;
import com.glut.news.mapper.CommentsMapper;
import com.glut.news.mapper.VideoMapper;
import com.glut.news.vo.Article;
import com.glut.news.vo.Comments;
import com.glut.news.vo.DataList;
import com.glut.news.vo.JsonModel;
import com.glut.news.vo.TouTiaoMultiNewsVideoContentBean;
import com.glut.news.vo.TouTiaoMultiNewsVideoMainBean;
import com.glut.news.vo.TouTiaoVideoComments;
import com.glut.news.vo.TouTiaoMultiNewsVideoMainBean.DataBean;
import com.glut.news.vo.TouTiaoVideoContentBean;
import com.glut.news.vo.Video;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class NetUtils {

	

	// 爬取凤凰新闻频道详情
	public static void bugs2(String url, VideoMapper videoMapper, ArticleMapper mArticleMapper2,
			CommentsMapper commentMapper, String type) {

		try {
			// 这是get方式得到的
			Document doc = Jsoup.connect(url).timeout(10000)
					.header("User-Agent",
							"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2")
					.get();

			Elements links = doc.select(".news_li");
			if (links.size() == 0) {
				// 视频列表爬取
				Elements videoLink = doc.select(".video_news");

				for (Element element : videoLink) {
					Video v = new Video();
					Elements elements = element.children();
					String videoTime = elements.get(0).child(1).text();
					String imageUrl = elements.get(0).child(2).attr("abs:src");
					String detailUrl = elements.get(1).attr("abs:href");
					/*
					 * String title=elements.get(1).text(); String
					 * author=elements.get(3).child(0).text(); String
					 * time=elements.get(3).child(1).text();
					 */
					v.setVideo_Image(imageUrl);
					v.setVideo_time(videoTime);
					parseVideoDetail(detailUrl, v, videoMapper, commentMapper);

				}

			} else {

				// 图文新闻抓取
				for (Element link : links) {

					Elements childrenDivs = link.children();
					if (childrenDivs.size() == 0) {

					} else {
						String videoTime = childrenDivs.get(0).getElementsByTag("span").text();

						if (!"".equals(videoTime)) {
							// 视频新闻

						} else {
							// 图文新闻
							String imageUrl = childrenDivs.get(0).getElementsByTag("img").attr("abs:src");
							String titleString = childrenDivs.get(1).text();
							//String detailUrl = childrenDivs.get(1).getElementsByTag("a").attr("abs:href");
							String author = childrenDivs.get(2).getElementsByTag("a").text();

							//String comments = childrenDivs.get(2).getElementsByClass("trbszan").text();
							Article article2 = new Article();
							article2.setArticle_Title(titleString);
							article2.setArticle_Image(imageUrl);
							article2.setArticle_Author_name(author);
							article2.setArticle_Type(type);

							//parseImageTextNews(detailUrl, article2, mArticleMapper2, commentMapper);

						}
					}

				}

			}

			// System.out.println("***************");
			// 另外一种是post方式
			/*
			 * @SuppressWarnings("unused") Document doc_Post =
			 * Jsoup.connect(url) .data("query","Java") .userAgent("I am jsoup")
			 * .cookie("auth","token") .timeout(10000) .post(); Elements
			 * links_Post = doc_Post.select("a[href]"); for(Element
			 * link:links_Post){ String linkHref = link.attr("abs:href"); String
			 * linkText = link.text();
			 * //System.out.println(linkText+":"+linkHref);
			 * 
			 * hm.put(linkText, linkHref); }
			 */

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		// return hm ;

	}

	// 解析图文详情页
	private static void parseImageTextNews(String detailUrl, Article article2, ArticleMapper mArticleMapper2,
			CommentsMapper commentMapper) {
		try {
			Document doc = Jsoup.connect(detailUrl).timeout(10000)
					.header("User-Agent",
							"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2")
					.get();
			Elements elements = doc.select(".news_about");
			Elements elements2 = doc.select(".news_txt");
			Elements elements3 = doc.select(".zan");
			Elements elements4 = doc.select(".news_keyword");
			Elements elements5 = doc.select("#hidden_contid");

			String keyWords = null;
			String likesString = "";
			String time = "";
			String contentString = "";
			String contId = "";

			for (Element element : elements) {
				time = element.child(1).text();
			}

			for (Element element : elements2) {
				contentString = element.html();
			}
			for (Element element : elements3) {
				likesString = element.text();
			}
			for (Element element : elements4) {
				keyWords = element.text().substring(6, element.text().length());
			}
			for (Element element : elements5) {
				contId = element.attr("value").trim();
			}

			article2.setArticle_KeyWords(keyWords);
			article2.setArticle_Likes(Integer.parseInt(likesString));
			article2.setArticle_Content(contentString);
			article2.setArticle_Time(time);
			if (!"".equals(contId)) {
				article2.setArticle_Id(Integer.parseInt(contId));

			}
			//System.out.println("---------------------");
			//System.out.println(article2);

			String commitUrl = "http://www.thepaper.cn/newDetail_commt.jsp?contid=" + contId;
			// 获得评论信息
			//System.out.println("-------以下为评论---------");
			article2.setArtilce_Comments(getCommits(commitUrl, contId, commentMapper));
			mArticleMapper2.insertArticle(article2);
			//System.out.println("---------------------");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 解析评论信息
	private static int getCommits(String url, String contId, CommentsMapper commentMapper) {

		String moreCommitUrl = "http://www.thepaper.cn/load_moreFloorComment.jsp?contid=" + contId + "&startId=";
		Elements elements = null;
		int x = 0;
		try {
			Document doc = Jsoup.connect(url).timeout(10000)
					.header("User-Agent",
							"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2")
					.get();
			Elements elements6 = doc.select("div[startId]");
			elements = doc.select(".comment_que");

			String startId = "";
			String author_logo = "";
			String author_name = "";
			String commit_content = "";
			String commit_time = "";
			String dianzan = "";
			String commit_Id = "";

			if (elements.size() != 0) {
				for (Element element : elements) {
					String idString = element.attr("id");

					if (!idString.contains("Hot")) {
						x++;
						commit_Id = idString.substring(10, idString.length());
						author_logo = element.child(0).child(0).child(0).getElementsByTag("img").attr("abs:src");
						author_name = element.child(0).child(1).child(0).getElementsByTag("a").text();
						commit_time = element.child(0).child(1).child(0).getElementsByTag("span").text();

						if (commit_time.contains("分钟")) {
							int cru_minute = DateUtils.getDate2().get(Calendar.MINUTE);
							int mimute = Integer.parseInt(commit_time.substring(0, commit_time.length() - 3).trim());
							if (cru_minute > mimute) {
								int time2 = cru_minute - mimute;
								commit_time = DateUtils.getDate2().get(Calendar.YEAR) + "-"
										+ DateUtils.getDate2().get(Calendar.MONTH) + "-"
										+ DateUtils.getDate2().get(Calendar.DATE) + "-"
										+ DateUtils.getDate2().get(Calendar.HOUR) + ":" + time2;
							} else {
								int time2 = 60 + cru_minute - mimute;
								int hour = DateUtils.getDate2().get(Calendar.HOUR_OF_DAY) - 1;
								commit_time = DateUtils.getDate2().get(Calendar.YEAR) + "-"
										+ DateUtils.getDate2().get(Calendar.MONTH) + "-"
										+ DateUtils.getDate2().get(Calendar.DATE) + "-" + hour + ":" + time2;
							}

						} else if (commit_time.contains("小时")) {
							int hour = DateUtils.getDate2().get(Calendar.HOUR_OF_DAY);
							int time2 = hour
									- Integer.parseInt(commit_time.substring(0, commit_time.length() - 3).trim());
							commit_time = DateUtils.getDate2().get(Calendar.YEAR) + "-"
									+ DateUtils.getDate2().get(Calendar.MONTH) + "-"
									+ DateUtils.getDate2().get(Calendar.DATE) + "-" + time2 + ":"
									+ DateUtils.getDate2().get(Calendar.MINUTE);
						}

						commit_content = element.child(0).child(1).getElementsByClass("ansright_cont").text();
						dianzan = element.child(0).child(1).getElementsByClass("ansright_time").get(0).child(0).text()
								.trim();

						Comments comment = new Comments();
						comment.setAuthor_logo(author_logo);
						comment.setAuthor_name(author_name);
						comment.setComment_Content(commit_content);
						comment.setComment_Time(commit_time);
						comment.setComment_Article(Integer.parseInt(contId));
						comment.setComment_Id(Integer.parseInt(commit_Id));
						if ("".equals(dianzan)) {
							comment.setLikes(0);
						} else {
							if (dianzan.contains("k")) {
								int commit = (int) ((Double
										.parseDouble(dianzan.substring(0, dianzan.length() - 1).trim())) * 1000);
								comment.setLikes(commit);
							} else {
								comment.setLikes(Integer.parseInt(dianzan));
							}
						}
						// 保存评论
						if (!comment.getComment_Content().equals("")) {
							commentMapper.insertComment(comment);

						}
						//System.out.println(comment.toString());
					}

				}
			}

			for (Element element : elements6) {
				startId = element.attr("startId").trim();
			}
			if (!startId.equals("0")) {
				x += getCommits(moreCommitUrl + startId, contId, commentMapper);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;

	}

	// 解析视频详情页
	private static void parseVideoDetail(String detailUrl, Video v, VideoMapper videoMapper,
			CommentsMapper commentMapper) {
		// 这是get方式得到的
		try {
			Document doc = Jsoup.connect(detailUrl).timeout(10000)
					.header("User-Agent",
							"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2")
					.get();

			Elements elements = doc.select(".video_news_detail ");
			Elements elements2 = doc.select(".video_txt_detail ");
			Elements elements5 = doc.select("#hidden_contid");

			String contId = "";
			String video_date = "";
			String video_from = "";
			String video_commit = "";
			String video_title = "";
			String video_abstract = "";
			String video_dianzan = "";
			String video_keyWord = "";
			String video_authorName = "";
			String video_authorLogo = "";
			String videoPlayerUrl2 = "";
			// 获取视频播放地址
			for (Element element : elements) {
				videoPlayerUrl2 = element.child(0).child(0).getElementsByTag("source").attr("src");

			}

			for (Element element : elements2) {
				video_title = element.child(0).text();
				video_abstract = element.child(1).child(0).text();

				Elements video_divs = element.child(1).child(1).child(0).child(0).getElementsByTag("span");
				for (int i = 0; i < video_divs.size(); i++) {
					if (i == 0) {
						video_date = video_divs.get(i).text();

					} else if (i == 1) {
						video_from = video_divs.get(i).text();

					} else {
						video_commit = video_divs.get(i).text();

					}

				}

				video_dianzan = element.child(1).child(1).child(0).child(1).child(0).text();
				String kString = element.child(1).child(1).child(0).child(1).child(2).text();
				video_keyWord = kString.substring(3, kString.length());
				video_authorName = element.child(2).child(1).child(1).child(0).text();
				video_authorLogo = element.child(2).child(1).child(0).child(0).attr("abs:src");

				/*
				 * java.util.Date date = null; try { date = new
				 * SimpleDateFormat("yyyy-MM-dd hh:mm").parse(video_date); }
				 * catch (ParseException e) { // TODO Auto-generated catch block
				 * e.printStackTrace(); } java.sql.Date sqlDate = new
				 * java.sql.Date(date.getTime());
				 */

			}

			/*
			 * 获取 1.视频详细信息： 1.1标题： 1.2发表时间 1.3摘要 1.4来源 1.5评论数 1.6点赞数 1.7关键字
			 * 2.作者信息 2.1作者名字 2.2作者logo
			 */

			v.setVideo_Player(videoPlayerUrl2);
			v.setVideo_Title(video_title);
			v.setVideo_Abstract(video_abstract);
			if (!"".equals(video_date)) {
				v.setVideo_PutTime(video_date);
			}

			v.setVideo_Author_Name(video_authorName);
			v.setVideo_Author_Logo(video_authorLogo);
			if (!"".equals(contId)) {
				v.setVideo_Id(Integer.parseInt(contId));
			}

			v.setVideo_From(video_from);
			if (!video_dianzan.equals("")) {
				if (video_dianzan.contains("k")) {
					int dianzan = (int) ((Double
							.parseDouble(video_dianzan.substring(0, video_dianzan.length() - 1).trim())) * 1000);
					v.setVideo_Likes(dianzan);
				} else {
					v.setVideo_Likes(Integer.parseInt(video_dianzan));
				}

			} else {
				v.setVideo_Likes(0);

			}
			for (Element element : elements5) {
				contId = element.attr("value").trim();
			}
			if (!"".equals(contId)) {
				v.setVideo_Id(Integer.parseInt(contId));
			}

			//System.out.println(v.toString());

			String commitUrl = "http://www.thepaper.cn/newDetail_commt.jsp?contid=" + contId;
			// 获得评论信息
			//System.out.println("-------以下为评论---------");
			v.setVideo_Comments(getCommits(commitUrl, contId, commentMapper));
			// 保存video
			videoMapper.insertVideo(v);

			//System.out.println("---------------------");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	// 爬取彭拜新闻频道
	public static void bugs(String url, VideoMapper videoMapper, ArticleMapper mArticleMapper2,
			CommentsMapper commentMapper) {
		try {
			// 这是get方式得到的
			Document doc = Jsoup.connect(url).timeout(10000)
					.header("User-Agent",
							"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2")
					.get();

			Elements links = doc.select(".bn_a");
			for (Element link : links) {

				String channel = link.attr("abs:href");
				String type = link.text();
				bugs2(channel, videoMapper, mArticleMapper2, commentMapper, type);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block

		}

	}



	public static void bugs6(String url, VideoMapper videoMapper, ArticleMapper articleMapper,
			CommentsMapper commentMapper) {


		try {
			Document doc = Jsoup.connect(url).timeout(10000)
					.header("User-Agent",
							"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2")
					.get();
			// 获取频道列表
			Elements elements = doc.select(".nav_menu .nav_menu_item");
			for (Element element : elements) {
				String channel = element.attr("abs:href");// 获取频道href
				String channelName = element.text();// 获取频道名
				// 解析各频道数据
				parseChannelList(channel, channelName, videoMapper, articleMapper, commentMapper);
			}
			elements=doc.select(".nav_item ");
			
			for (Element element : elements) {
				String channel = element.attr("abs:href");// 获取频道href
				String channelName = element.text();// 获取频道名
				// 解析各频道数据
				parseChannelList(channel, channelName, videoMapper, articleMapper, commentMapper);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 解析各频道数据
	private static void parseChannelList(String channel, String channelName, VideoMapper videoMapper,
			ArticleMapper articleMapper, CommentsMapper commentMapper) {

		try {
			Document doc = Jsoup.connect(channel).timeout(10000)
					.header("User-Agent",
							"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2")
					.get();
			
			Article article=new Article();
			
			
			Elements elements=doc.select(".figure ");
			for (Element element : elements) {
				String newsDetailUrl=element.child(0).child(0).child(0).attr("abs:href");
				String newsTitle=element.child(0).child(0).text();
				String newsImage = null;
				Elements elements2=element.select("a");
				if (elements2.size()==2) {
					 newsImage=element.child(1).attr("style");
					newsImage="http:"+newsImage.substring(21,element.child(1).attr("style").length()-2);
				}
				
				Elements childrenDic=element.child(0).child(1).getElementsByTag("span");
				
				SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");//设置日期格式
				//System.out.println(df.format(new Date()));
				int newsId=Integer.parseInt(df.format(new Date()));
				String author="";
				String time="";
				String comment="";
				for (int i = 0; i < childrenDic.size(); i++) {
					if (i==0) {
						author=childrenDic.get(i).text();
					}
					if (i==1) {
						time=childrenDic.get(i).text();
					}
					
					if (i==2) {
						comment=childrenDic.get(i).text();
						if (!"".equals(comment)) {
							comment=childrenDic.get(i).text().substring(0,childrenDic.get(i).text().length()-2);

						}
					}
					
				}
				if (time.contains("分钟")) {
					int cru_minute = DateUtils.getDate2().get(Calendar.MINUTE);
					int mimute = Integer.parseInt(time.substring(0, time.length() - 3).trim());
					if (cru_minute > mimute) {
						int time2 = cru_minute - mimute;
						time = DateUtils.getDate2().get(Calendar.YEAR) + "-"
								+ DateUtils.getDate2().get(Calendar.MONTH) + "-"
								+ DateUtils.getDate2().get(Calendar.DATE) + "-"
								+ DateUtils.getDate2().get(Calendar.HOUR) + ":" + time2;
					} else {
						int time2 = 60 + cru_minute - mimute;
						int hour = DateUtils.getDate2().get(Calendar.HOUR_OF_DAY) - 1;
						time = DateUtils.getDate2().get(Calendar.YEAR) + "-"
								+ DateUtils.getDate2().get(Calendar.MONTH) + "-"
								+ DateUtils.getDate2().get(Calendar.DATE) + "-" + hour + ":" + time2;
					}

				} else if (time.contains("小时")) {
					int hour = DateUtils.getDate2().get(Calendar.HOUR_OF_DAY);
					int time2 = hour
							- Integer.parseInt(time.substring(0, time.length() - 3).trim());
					time = DateUtils.getDate2().get(Calendar.YEAR) + "-"
							+ DateUtils.getDate2().get(Calendar.MONTH) + "-"
							+ DateUtils.getDate2().get(Calendar.DATE) + "-" + time2 + ":"
							+ DateUtils.getDate2().get(Calendar.MINUTE);
				}
				
				article.setArticle_Id(newsId);
				article.setArticle_Image(newsImage);
				article.setArticle_Title(newsTitle);
				article.setArticle_Time(time);
				article.setArticle_Type(channelName);
				article.setArticle_Author_name(author);
				article.setArticle_Url(newsDetailUrl);
				if (!"".equals(comment)) {
					article.setArtilce_Comments(Integer.parseInt(comment.trim()));

				}
				//文章详情页解析
				parseNewsDetail(newsDetailUrl,article,articleMapper,commentMapper);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

	}

	private static void parseNewsDetail(String newsDetailUrl, Article article, ArticleMapper articleMapper,
			CommentsMapper commentMapper) {

		Document doc;
		try {
			doc = Jsoup.connect(newsDetailUrl).timeout(10000)
					.header("User-Agent",
							"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2")
					.get();
			String content=doc.select(".article_content").eq(0).select("#content").outerHtml().replace("\n", "").replace("data-original","src").replace("data-", "");
			String keyWords=doc.select(".article_more").eq(0).select("a").text();
			//keyWords=keyWords.substring(keyWords.length()-5,keyWords.length());
			Elements comment=doc.select(".comment_item ");
			String comment_AuthorLogo="";
			String comment_like="";
			String comment_Author_Name="";
			String comment_content="";
			String comment_Time="";
			//System.out.println("------------");
			article.setArticle_Content(content);
			article.setArticle_KeyWords(keyWords);
			articleMapper.insertArticle(article);
			//System.out.print(article.toString());
			//System.out.print("-----评论-------");
			for (Element element2 : comment) {
				String comentDiv=element2.child(0).child(0).child(0).attr("style");
				if (!"".equals(comentDiv)) {
					 comment_AuthorLogo=comentDiv.substring(21,comentDiv.length()-2);

				}
				 comment_like=element2.child(0).child(1).child(0).text();
				 comment_Author_Name=element2.child(0).child(1).child(1).text();
				 comment_Time=element2.child(0).child(1).child(2).text();
				 comment_content=element2.child(0).child(1).child(3).text();
				 
				 if (comment_Time.contains("分钟")) {
						int cru_minute = DateUtils.getDate2().get(Calendar.MINUTE);
						int mimute = Integer.parseInt(comment_Time.substring(0, comment_Time.length() - 3).trim());
						if (cru_minute > mimute) {
							int time2 = cru_minute - mimute;
							comment_Time = DateUtils.getDate2().get(Calendar.YEAR) + "-"
									+ DateUtils.getDate2().get(Calendar.MONTH) + "-"
									+ DateUtils.getDate2().get(Calendar.DATE) + "-"
									+ DateUtils.getDate2().get(Calendar.HOUR) + ":" + time2;
						} else {
							int time2 = 60 + cru_minute - mimute;
							int hour = DateUtils.getDate2().get(Calendar.HOUR_OF_DAY) - 1;
							comment_Time = DateUtils.getDate2().get(Calendar.YEAR) + "-"
									+ DateUtils.getDate2().get(Calendar.MONTH) + "-"
									+ DateUtils.getDate2().get(Calendar.DATE) + "-" + hour + ":" + time2;
						}

					} else if (comment_Time.contains("小时")) {
						int hour = DateUtils.getDate2().get(Calendar.HOUR_OF_DAY);
						int time2 = hour
								- Integer.parseInt(comment_Time.substring(0, comment_Time.length() - 3).trim());
						comment_Time = DateUtils.getDate2().get(Calendar.YEAR) + "-"
								+ DateUtils.getDate2().get(Calendar.MONTH) + "-"
								+ DateUtils.getDate2().get(Calendar.DATE) + "-" + time2 + ":"
								+ DateUtils.getDate2().get(Calendar.MINUTE);
					}
					
				
					Comments comment1= new Comments();
						comment1.setAuthor_name(comment_Author_Name);
						comment1.setComment_Content(comment_content);
						comment1.setAuthor_logo(comment_AuthorLogo);
						comment1.setComment_Time(comment_Time);
						comment1.setComment_Article(article.getArticle_Id());
						if (!comment1.getComment_Content().equals("")) {
							commentMapper.insertComment(comment1);

						}
			}
			
			
		
		
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void bugs7(String url, VideoMapper videoMapper, ArticleMapper articleMapper,
			CommentsMapper commentMapper) {
	
	try {
		Document document=	Jsoup.connect(url).timeout(10000).header("User_Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:58.0) Gecko/20")
				          .get();
		
		Elements elements=document.select(".menuList li");
		for (Element element : elements) {
			String channelUrl=element.select("a").attr("abs:href");
			String channelName=element.text();
			if (!"两性".equals(channelName)) {
				parseChannelList2(channelUrl, channelName, videoMapper, articleMapper, commentMapper);

			}
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
			;

		
	}

	private static void parseChannelList2(String channelUrl, String channelName, VideoMapper videoMapper,
			ArticleMapper articleMapper, CommentsMapper commentMapper) {

		try {
			Document document=	Jsoup.connect(channelUrl).timeout(10000).header("User_Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:58.0) Gecko/20")
			          .get();
			
			Elements elements=document.select(".infoList li");
			for (Element element : elements) {
				String video_time=element.child(0).child(0).text();
				String video_detailUrl=element.child(0).child(1).attr("abs:href");
				String video_imageUrl=element.child(0).child(1).child(0).attr("src");
				String video_title=element.child(1).text();
				String video_authorName=element.child(2).text();
				String video_plays=element.child(3).text();
				
				SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");//设置日期格式
				//System.out.println(df.format(new Date()));
				int video_Id=Integer.parseInt(df.format(new Date()));
				Video video=new Video();
				video.setVideo_Title(video_title);
				video.setVideo_Author_Name(video_authorName);
				video.setVideo_Image(video_imageUrl);
				video.setVideo_Type(channelName);
				video.setVideo_From("第一视频");
				video.setVideo_Players(Integer.parseInt(video_plays));
				video.setVideo_Id(video_Id);
				video.setVideo_time(video_time);
				
				parseDetailPage(video,video_detailUrl,videoMapper,commentMapper);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void parseDetailPage(Video video, String video_detailUrl, VideoMapper videoMapper, CommentsMapper commentMapper) {
		try {
			Document document=	Jsoup.connect(video_detailUrl).timeout(10000).header("User_Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:58.0) Gecko/20")
			          .get();
			
			String video_putTime=document.select(".video_infoBar").get(0).text();
			String[] video_players=document.select(".videoBox").get(0).getElementsByTag("embed").attr("flashvars").split("=");
			String video_player=video_players[3];
			String video_authorLogo=document.select(".userTx").get(0).getElementsByTag("img").attr("src");
			video.setVideo_Author_Logo(video_authorLogo);
			video.setVideo_PutTime(video_putTime);
			video.setVideo_Player(video_player);
			videoMapper.insertVideo(video);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void bugs8(String url, VideoMapper videoMapper, CommentsMapper commentsMapper){
		bugsTouTiaoVideo(url, commentsMapper,  videoMapper);
	
		
	}



private static void bugsTouTiaoVideo(String url, CommentsMapper commentsMapper, VideoMapper videoMapper) {

	String []categoryId=new String[]{"subv_voice","subv_funny","subv_society","subv_comedy","subv_life","subv_movie","subv_entertainment","subv_cute","subv_game","subv_boutique","subv_broaden_view"};
	String categoryString[]=new String[]{"音乐","搞笑","社会","小品","生活","影视","娱乐","呆萌","游戏","原创","开眼"};
	String max_behot_time=String.valueOf(System.currentTimeMillis() / 1000);;
	
	for (int i = 0; i < categoryId.length; i++) {
		String string = categoryId[i];
		
	
			String post="category="+string+"&max_behot_time="+max_behot_time;
		JsonObject res = null;
		res = touTiaoVideo(url,post);
		 Gson gson=new Gson();
		 TouTiaoMultiNewsVideoMainBean jsonModel= gson.fromJson(res, TouTiaoMultiNewsVideoMainBean.class);
		 System.out.println(jsonModel.toString());
		 for (DataBean string2 : jsonModel.getData()) {
			
			 TouTiaoMultiNewsVideoContentBean t2=gson.fromJson(string2.getContent(), TouTiaoMultiNewsVideoContentBean.class);
			if (t2!=null) {
				 bugsVideoDetail(t2.getVideo_id(),t2,commentsMapper,videoMapper,categoryString[i]);

			}
		}
		
		
		
		
		
		
	
	}
}

private static void bugsVideoDetail(String video_id,TouTiaoMultiNewsVideoContentBean t,CommentsMapper cMapper,VideoMapper vMapper,String  c) {
	String url=getVideoContentApi(video_id);
	JsonObject res = null;
	res = getXpath(url);
	 Gson gson=new Gson();
	 Video v=new Video();
	 TouTiaoVideoContentBean jsonModel= gson.fromJson(res, TouTiaoVideoContentBean.class);
	if (jsonModel.getData()!=null) {
		 if (jsonModel.getData().getVideo_list()!=null) {
			 if (jsonModel.getData().getVideo_list().getVideo_3() != null) {
				
		         String base64 = jsonModel.getData().getVideo_list().getVideo_3().getMain_url();
		         String url2 = (new String(Base64.getDecoder().decode(base64)));
		         System.out.print("视频地址："+url2);
		         v.setVideo_Player(url2);
		        
			 }
			 
			 if (jsonModel.getData().getVideo_list().getVideo_2()!= null) {
		    	  String base64 = jsonModel.getData().getVideo_list().getVideo_2().getMain_url();
		          String url2 = (new String(Base64.getDecoder().decode(base64)));
		          System.out.print("视频地址："+url2);
		          v.setVideo_Player(url2);
		     }

		     if (jsonModel.getData().getVideo_list().getVideo_1()!= null) {
		    	  String base64 = jsonModel.getData().getVideo_list().getVideo_1().getMain_url();
		          String url2 = (new String(Base64.getDecoder().decode(base64)));
		          System.out.print("视频地址："+url2);
		          v.setVideo_Player(url2);
		          
		         
		     }
			 
			 
		     }
	}
	

    
     String regEx="[^0-9]";   
     Pattern p = Pattern.compile(regEx);   
     Matcher m = null;
     if (t.getVideo_id()!=null) {
        m= p.matcher(t.getVideo_id());   

	
     String video_ids= m.replaceAll("").trim().toString();
     if (video_ids.length()>=5) {
    	 video_ids=video_ids.substring(0, 5);
	}
     System.out.println( "id"+video_ids);
	 System.out.println(jsonModel.toString());
	 	String groupId=t.getGroup_id()+"";
	 	t.setVideo_id(video_ids);
        getVideoDetailComments(groupId,t ,cMapper,vMapper);//获取评论数据
	
        v.setVideo_Id(Integer.parseInt(t.getVideo_id()));
        v.setVideo_Abstract(t.getAbstractX());
        v.setVideo_Title(t.getTitle());
        v.setVideo_Comments(t.getComment_count());
        v.setVideo_Author_Logo(t.getUser_info().getAvatar_url());
        v.setVideo_Author_Name(t.getUser_info().getName());
        v.setVideo_From("西瓜视频");
        v.setVideo_KeyWord(t.getKeywords());
        v.setVideo_Image(t.getVideo_detail_info().getDetail_video_large_image().getUrl());
        v.setVideo_Likes(t.getDigg_count());
        v.setVideo_Players(t.getVideo_detail_info().getVideo_watch_count());
        v.setVideo_PutTime("");
        //v.setVideo_Size(t.getVideo_detail_info().get);
        v.setVideo_Type(c);
        vMapper.insertVideo(v);//保存视频信息
     }
}

private static void getVideoDetailComments(String groupId,TouTiaoMultiNewsVideoContentBean t,CommentsMapper cMapper,VideoMapper vMapper) {
String commentUrl="http://is.snssdk.com/article/v53/tab_comments/";
JsonObject res = null;
int off=0;
for (int i = 0; i < t.getComment_count(); i+=20) {
	
String post="group_id="+groupId+"&offset="+i;


res = touTiaoVideo(commentUrl,post);
 Gson gson=new Gson();
 TouTiaoVideoComments touTiaoComment= gson.fromJson(res, TouTiaoVideoComments.class);

 for (TouTiaoVideoComments.DataBean dataBean : touTiaoComment.getData()) {
	 Comments comments=new Comments();
	
	 comments.setComment_Article(Integer.parseInt(t.getVideo_id()));
	 comments.setAuthor_logo(dataBean.getComment().getUser_profile_image_url());
	 comments.setAuthor_name(dataBean.getComment().getUser_name());
	 comments.setComment_Content(dataBean.getComment().getText());
	 comments.setLikes(dataBean.getComment().getDigg_count());
	 if (!comments.getComment_Content().equals("")) {
		 cMapper.insertComment(comments);//保存评论到数据库

	}
	
}
 
}

 
}

/*获取视频详情页Url*/
private static String getVideoContentApi(String videoid) {
    String VIDEO_HOST = "http://ib.365yg.com";
    String VIDEO_URL = "/video/urls/v/1/toutiao/mp4/%s?r=%s";
    String r = getRandom();
    String s = String.format(VIDEO_URL, videoid, r);
    // 将/video/urls/v/1/toutiao/mp4/{videoid}?r={Math.random()} 进行crc32加密
    CRC32 crc32 = new CRC32();
    crc32.update(s.getBytes());
    String crcString = crc32.getValue() + "";
    String url = VIDEO_HOST + s + "&s=" + crcString;
    return url;
}

private static String getRandom() {
    Random random = new Random();
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < 16; i++) {
        result.append(random.nextInt(10));
    }
    return result.toString();
}


/**
 * 该方法用于对V电影的评论信息解析
 * 发起http请求并获取结果
 * @param requestUrl 请求地址
 */
public static JsonObject getXpath(String requestUrl){
    String res="";
    JsonObject object = null;
    StringBuffer buffer = new StringBuffer();
    try{
        URL url = new URL(requestUrl);
        HttpURLConnection urlCon= (HttpURLConnection)url.openConnection();
        if(200==urlCon.getResponseCode()){
            InputStream is = urlCon.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"utf-8");
            BufferedReader br = new BufferedReader(isr);

            String str = null;
            while((str = br.readLine())!=null){
                buffer.append(str);
            }
            br.close();
            isr.close();
            is.close();
            res = buffer.toString();
            JsonParser parse =new JsonParser();
            object = (JsonObject) parse.parse(res);
        }
    }catch(IOException e){
        e.printStackTrace();
    }
    return object;
}

/*该方法用于对V电影的评论信息解析
返回JsonObject*/
public static JsonObject touTiaoVideo(String path,String post){
    URL url = null;
    try {
        url = new URL(path);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");// 提交模式
        // conn.setConnectTimeout(10000);//连接超时 单位毫秒
        // conn.setReadTimeout(2000);//读取超时 单位毫秒
        // 发送POST请求必须设置如下两行
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        // 获取URLConnection对象对应的输出流
        PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
        // 发送请求参数
        printWriter.write(post);//post的参数 xx=xx&yy=yy
        // flush输出流的缓冲
        printWriter.flush();
        //开始获取数据
        BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len;
        byte[] arr = new byte[1024];
        while((len=bis.read(arr))!= -1){
            bos.write(arr,0,len);
            bos.flush();
        }
        bos.close();
        JsonParser parse = new JsonParser();
        return (JsonObject)parse.parse(bos.toString("utf-8"));
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}


}
