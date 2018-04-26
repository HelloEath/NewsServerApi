package com.glut.news.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.glut.news.mapper.ArticleMapper;
import com.glut.news.mapper.HistoryMapper;
import com.glut.news.mapper.UserMapper;
import com.glut.news.mapper.VideoMapper;
import com.glut.news.service.IHistoryService;
import com.glut.news.vo.Article;
import com.glut.news.vo.History;
import com.glut.news.vo.Page;
import com.glut.news.vo.UserInfo;
import com.glut.news.vo.Video;
import com.google.gson.Gson;

@Service
public class HistoryServiceImpl implements IHistoryService {

	@Resource
	HistoryMapper historyMapper;
	@Resource
	UserMapper userMapper;
	
	@Resource
	VideoMapper videoMapper;
	
	@Resource
	ArticleMapper mArticleMapper;
	@Override
	public int getTatolHistory(int userId) {
		
		return historyMapper.selectTatolByUserId(userId);
		
	}

	@Override
	public List<History> selectUserHistoryService(Page<History> page2) {
	List<History> hList=historyMapper.selectArticleByUserId(page2);
	List<History> hList2=historyMapper.selectVideoByUserId(page2);
	List<History> h3=new ArrayList<History>();
	h3.addAll(hList);
	h3.addAll(hList2);
		return h3;
	}

	@Override
	public int putHistoryService(History history,HttpSession hSession) {
		
		int x=historyMapper.selectedHistoryByUserIdWithContentId(history);
		if (x==1) {
			x=0;
		}else {
			String sidString=hSession.getId();
			UserInfo userInfo=(UserInfo)hSession.getAttribute("User");
			userInfo.setUser_Historys(userInfo.getUser_Historys()+1);
			
			Gson gson= new Gson();
			Map<String, Double> m=new HashMap<String, Double>();
			m=gson.fromJson(userInfo.getUser_Interest(),m.getClass());
		
			if (m.containsKey(history.getContent_type())) {
				m.replace(history.getContent_type(), m.get(history.getContent_type())+1);//如果浏览的文章/视频类型包含在用户已存在的兴趣点，则为该兴趣点值加一，并替换原来值
			}else {
				m.put(history.getContent_type(), 1.0);//如果浏览的文章/视频类型不包含在用户已存在的兴趣点，则为用户新添加一个兴趣点，并设初始值1
			
			}
			if (history.getHistory_Type()==1) {//是文章
				   if (userInfo!=null) {
	            		
	            			Article article=new Article();
	            			article.setArticle_Id(history.getHistory_Article());
	            			 article=mArticleMapper.selectById(article);
	            			article.setArticle_Looks(article.getArticle_Looks()+1);
	            			// TODO Auto-generated method stub
	            			mArticleMapper.updateArticle(article);// 更新阅览量
	            		}
			}else {//是视频
				Video rVideo=new Video();
				rVideo.setVideo_Id(history.getHistory_Article());
				rVideo=videoMapper.selectById(rVideo);
				rVideo.setVideo_Players(rVideo.getVideo_Players()+1);
				// TODO Auto-generated method stub
				 videoMapper.updateVideo(rVideo);//更新视频播放量
			}
			
			userInfo.setUser_Interest(gson.toJson(m).toString());//保存新的兴趣点数据
			hSession.setAttribute("User", userInfo);
			userMapper.updateUser(userInfo);
			return historyMapper.insertHistory(history);

		}
		
		return x;
	}

}
