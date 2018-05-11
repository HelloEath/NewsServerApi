package com.glut.news.commons;

import com.glut.news.mapper.ArticleMapper;
import com.glut.news.mapper.VideoMapper;
import com.glut.news.vo.Article;
import com.glut.news.vo.Video;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class LuceneDaoTest {
    private LuceneDao luceneDao=new LuceneDao();
    @Resource
    ArticleMapper a;

    @Resource
	VideoMapper videoMapper;
	@Test
	public void testAddIndex() {
		 
	            try {
	            	//List<Video> videoList=videoMapper.selectAllVideo();
	            	List<Article> aList=a.selectAll();

	            	/*for (int i=0;i<videoList.size();i++){
						Video video=new Video();
						video.setVideo_Id(videoList.get(i).getVideo_Id());
						video.setVideo_Author_Name(videoList.get(i).getVideo_Author_Name());
						video.setVideo_Image(videoList.get(i).getVideo_Image());
						video.setVideo_PutTime(videoList.get(i).getVideo_PutTime());
						video.setVideo_Title(videoList.get(i).getVideo_Title());
						video.setVideo_Type(videoList.get(i).getVideo_Type());
						luceneDao.addIndex(video);
						System.out.println(video.toString());

					}*/
	            	 for(int i=0;i<aList.size();i++){
	     	            Article article=new Article();
	     	            article.setArticle_Id(aList.get(i).getArticle_Id());
	     	            article.setArticle_Title(aList.get(i).getArticle_Title());
	     	            article.setArticle_Image(aList.get(i).getArticle_Image());
	     	            article.setArticle_Author_name(aList.get(i).getArticle_Author_name());
	     	            article.setArticle_KeyWords(aList.get(i).getArticle_KeyWords());
	     	            article.setArticle_Type(aList.get(i).getArticle_Type());
	     	            article.setArticle_Time(aList.get(i).getArticle_Time());
	     	            luceneDao.addIndex(article);
	     	            System.out.println(article.toString());
	            	 }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	       
	    
	}

	@Test
	public void testFindIndex() {
		   String keywords="强";
	        //title content   textfield 现在使用的分词器是单字分词..
	        List<Object> listArticles;
			try {
				listArticles = luceneDao.findIndex(keywords,0,10);
				for(Object o:listArticles){
					if (o instanceof Video){
						Video video=(Video) o;
						System.out.println(video.getVideo_Id());
						System.out.println(video.getVideo_Title());
						System.out.println(video.getVideo_Image());
						System.out.println(video.getVideo_Author_Name());
						System.out.println(video.getVideo_PutTime());
						System.out.println(video.getVideo_Type());
					}else {
						Article article=(Article)o;
		            System.out.println(article.getArticle_Id());
		            System.out.println(article.getArticle_Author_name());
		            System.out.println(article.getArticle_Image());
		            System.out.println(article.getArticle_Title());
		            System.out.println(article.getArticle_Time());
		            System.out.println(article.getArticle_Type());
		        }
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    }
	


}
