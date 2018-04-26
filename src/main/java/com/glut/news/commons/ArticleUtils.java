package com.glut.news.commons;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import com.glut.news.vo.Article;

public class ArticleUtils {

    /**
     * 将article 转换成document
     * 无非article 的值设置document里面去...
     *
     *
     * @param article
     * @return
     */
    public static Document articleToDocument(Article article){
        
        Document document=new Document();
        
        IntField idfield=new IntField("Article_Id",article.getArticle_Id(),Store.YES);
        
        TextField ArticleTitle=new TextField("Article_Title", article.getArticle_Title(), Store.YES);
        StringField ArticleAuthorName=new StringField("Article_Author_name", article.getArticle_Author_name(), Store.YES);
        StringField ArticleImage=new StringField("Article_Image", article.getArticle_Image(), Store.YES);
        StringField ArticleKeyWords=new StringField("Article_KeyWords", article.getArticle_KeyWords(), Store.YES);
        StringField ArticleType=new StringField("Article_Type", article.getArticle_Type(), Store.YES);
        StringField ArticleTime=new StringField("Article_Time", article.getArticle_Time(), Store.YES);

        //TextField ArticleTime=new TextField("Article_Time", article.getArticle_Image(),Store.YES);

        //IntField ArticleLooks=new IntField("Article_Looks", article.getArticle_Looks(), Store.YES);
       // IntField ArticleLikes=new IntField("Article_Likes", article.getArticle_Likes(), Store.YES);
        
        //TextField ArticleImage=new TextField("Article_Image", article.getArticle_Image(),Store.YES);

        
       // TextField contentfield=new TextField("content", article.getContent(),Store.YES);
        
        document.add(idfield);
        document.add(ArticleTitle);
        
        document.add(ArticleAuthorName);
        document.add(ArticleImage);
        document.add(ArticleKeyWords);
        document.add(ArticleType);
        document.add(ArticleTime);

        return document;
        
    }
}
