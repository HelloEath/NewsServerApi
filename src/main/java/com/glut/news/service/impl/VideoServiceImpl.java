package com.glut.news.service.impl;

import com.glut.news.mapper.HistoryMapper;
import com.glut.news.mapper.UserMapper;
import com.glut.news.mapper.VideoMapper;
import com.glut.news.service.IVideoService;
import com.glut.news.vo.History;
import com.glut.news.vo.Page;
import com.glut.news.vo.UserInfo;
import com.glut.news.vo.Video;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class VideoServiceImpl implements IVideoService {

	@Resource
	VideoMapper videoMapper;
	@Resource
	HistoryMapper historyMapper;

	@Resource
	UserMapper userMapper;

	@Override
	public int getTaltolVideoService(Video v) {
		// TODO Auto-generated method stub
		return videoMapper.videoCounts(v);
	}

	@Override
	public List<Video> getVideoService(Page<Video> page2) {
		// TODO Auto-generated method stub
		return videoMapper.selectByPage(page2);
	}

	@Override
	public int putVideoService(Video video) {
		// TODO Auto-generated method stub
		return videoMapper.insertVideo(video);
	}

	@Override
	public int updateVideoService(Video video) {
		// TODO Auto-generated method stub
		return videoMapper.updateVideo(video);
	}

	@Override
	public int deleteVideoService(Video video) {
		// TODO Auto-generated method stub
		return videoMapper.deleteVideo(video);
	}

	@Override
	public List<Video> getTypeVideoService(Page<Video> page2) {
		// TODO Auto-generated method stub
		return videoMapper.selectByType(page2);
	}

	@Override
	public int updateVideoPlaysService(Video video) {
		Video VideoPlays = videoMapper.selectById(video);
		video.setVideo_Players(video.getVideo_Players() + 1);
		// TODO Auto-generated method stub
		return videoMapper.updateVideo(video);
	}

	@Override
	public List<Video> getVideoByKeyWords(Page<Video> page) {
		// TODO Auto-generated method stub
		return videoMapper.selectByKeyWords(page);
	}

	@Override
	public List<Video> getDetailVideoService(Page<Video> page) {
		// TODO Auto-generated method stub
		return videoMapper.selectByKeyWords(page);
	}

	@Override
	public int getKeyWordsTotalService(Video video) {
		// TODO Auto-generated method stub
		return videoMapper.selectByKeywordsTatol(video);
	}

	@Override
	public Video getVideoDetailService(Video video, HttpSession hSession) {
		Video video2 = videoMapper.selectById(video);
		UserInfo userInfo = (UserInfo) hSession.getAttribute("User");
		 Timer timer = new Timer();// 实例化Timer类  
	        timer.schedule(new TimerTask() {  
	            public void run() {  
	                System.out.println("退出");
					video2.setVideo_Players(video2.getVideo_Players() + 1);
					videoMapper.updateVideo(video2);// 更新视频播放量
	                if (userInfo!=null) {
	            		History history = new History();
	            		history.setHistory_Article(video.getVideo_Id());
	            		history.setHistory_Persion(userInfo.getUser_Id());
	            		int x = historyMapper.selectedHistoryByUserIdWithContentId(history);
	            		if (x == 1) {
	            			x = 0;
	            		} else {
	            			String sidString = hSession.getId();
	            			userInfo.setUser_Historys(userInfo.getUser_Historys() + 1);

	            			Gson gson = new Gson();
	            			Map<String, Double> m = new HashMap<String, Double>();
	            			m = gson.fromJson(userInfo.getUser_Interest(), m.getClass());

	            			if (m.containsKey(video2.getVideo_Type())) {
	            				m.replace(video2.getVideo_Type(), m.get(video2.getVideo_Type()) + 1);// 如果浏览的文章/视频类型包含在用户已存在的兴趣点，则为该兴趣点值加一，并替换原来值
	            			} else {
	            				if(!"".equals(video2.getVideo_Type()))
	            				m.put(video2.getVideo_Type(), 1.0);// 如果浏览的文章/视频类型不包含在用户已存在的兴趣点，则为用户新添加一个兴趣点，并设初始值1

	            			}


	            			// TODO Auto-generated method stub

	            			userInfo.setUser_Interest(gson.toJson(m).toString());// 保存新的兴趣点数据

	            			history.setHistory_Type(2);
	            			history.setHistory_Persion(userInfo.getUser_Id());
	            			history.setContent_type(video2.getVideo_Type());
	            			history.setHistory_Time(com.glut.news.commons.DateUtils.formatDate_getCurrentDateByF("YYYY-MM-DD"));

	            			historyMapper.insertHistory(history);// 插入新历史记录
	            			hSession.setAttribute("User", userInfo);//更新Session数据
	            			userMapper.updateUser(userInfo);// 保存用户新数据到数据库
	            		}

	            		}

	            }  
	        }, 5000);// 这里百毫秒  
	

		
		return video2;
	}

	@Override
	public void deleteRepeatVideoServer() {
		videoMapper.deleteRepeatVideo();
	}

	@Override
	public List<Video> selectAllVideoServer() {
		return videoMapper.selectAllVideo();
	}
}
