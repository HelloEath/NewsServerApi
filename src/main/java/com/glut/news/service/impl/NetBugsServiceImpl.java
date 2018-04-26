package com.glut.news.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.glut.news.commons.NetUtils;
import com.glut.news.mapper.ArticleMapper;
import com.glut.news.mapper.CommentsMapper;
import com.glut.news.mapper.VideoMapper;
import com.glut.news.service.INetBugsService;

@Service
public class NetBugsServiceImpl implements INetBugsService {

	@Resource
	VideoMapper videoMapper;
	@Resource
	ArticleMapper articleMapper;
	@Resource
	CommentsMapper commentMapper;

	/* 爬取澎湃新闻*/
	@Override
	public void bugsPengPaiWeb() {
		String url = "http://www.thepaper.cn";
		NetUtils.bugs(url, videoMapper, articleMapper, commentMapper);

	}

	/* 爬取Zaker新闻 */
	@Override
	public void bugsZakerWeb() {
		String url = "http://www.myzaker.com/";
		NetUtils.bugs6(url, videoMapper, articleMapper, commentMapper);

	}

	/* 爬取第一视频 */
	@Override
	public void bugsFirstNewsWeb() {
		String url = "http://www.v1.cn/";
		NetUtils.bugs7(url, videoMapper, articleMapper, commentMapper);

	}

	
	@Override
	public void bugsTouTiaoWeb() {
		// TODO Auto-generated method stub
		String url="http://is.snssdk.com/api/news/feed/v62/?iid=5034850950&device_id=6096495334&refer=1&count=20&aid=13";
		NetUtils.bugs8(url, videoMapper, commentMapper);
	}

	@Override
	public void bugsWeiBoWeb() {
		// TODO Auto-generated method stub
		
	}

}
