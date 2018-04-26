package com.glut.news.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.glut.news.mapper.ArticleMapper;
import com.glut.news.mapper.CommentsMapper;
import com.glut.news.mapper.VideoMapper;
import com.glut.news.service.ICommentService;
import com.glut.news.vo.Article;
import com.glut.news.vo.Comments;
import com.glut.news.vo.Page;
import com.glut.news.vo.Video;

@Service

public class CommentServiceImpl implements ICommentService {

	@Resource
	CommentsMapper mCommentMapper;
	@Resource
	VideoMapper mVideoMapper;
	
	@Resource
	ArticleMapper mArticle;

	@Override
	public int getCommentTotalService(Comments comments) {
		// TODO Auto-generated method stub
		return mCommentMapper.commentCounts(comments);
	}

	@Override
	public List<Comments> getCommentService(Page<Comments> page2) {
		// TODO Auto-generated method stub
		return mCommentMapper.selectByPage(page2);
	}

	@Override
	public int deleteCommentService(Comments comment) {
		// TODO Auto-generated method stub
		return mCommentMapper.deleteComment(comment);
	}

	@Override
	public int insertCommentService(Comments comment) {
		
		int x=mCommentMapper.insertComment(comment);
		return x;
	}

	@Override
	public int updateCommentService(String contentId, int type) {
	
		if (type==1) {
			int commentCount=mArticle.selectCommentCount(contentId);
			Article article=new Article();
			article.setArticle_Id(Integer.parseInt(contentId));
			article.setArtilce_Comments(commentCount+1);
			mArticle.updateArticle(article);//更新用户评论

		}else {
			int commentCount=mVideoMapper.selectCommentCount(contentId);

			Video v=new Video();
			v.setVideo_Id(Integer.parseInt(contentId));
			v.setVideo_Comments(commentCount+1);
			mVideoMapper.updateVideo(v);//更新用户评论
		}
		return 0;
	}
	
}
