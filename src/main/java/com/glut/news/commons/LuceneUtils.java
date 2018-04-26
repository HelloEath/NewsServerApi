package com.glut.news.commons;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class LuceneUtils {
private static Directory directory=null;
    
    private static IndexWriterConfig config=null;
    
    
    
    private static Version matchVersion=null;
    
    private static Analyzer analyzer=null;
    
    static{
        
        try {
            directory=FSDirectory.open(new File("D:/testLucence2"));//索引存放硬盘的位置
            
            matchVersion=Version.LUCENE_44;//lucene的版本
            
            analyzer=new StandardAnalyzer(matchVersion);//标准分词器，也是单字分词器
            
            config=new IndexWriterConfig(matchVersion, analyzer);//索引建立的配置
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    /**
     *
     *
     * @return返回用于操作索引的对象...
     * @throws IOException
     */
    public static IndexWriter getIndexWriter() throws IOException{
        IndexWriter indexWriter=new IndexWriter(directory,config);
        return indexWriter;
    }
    /**
     *
     * 返回用于读取索引的对象..
     * @return
     * @throws IOException
     */
    public static IndexSearcher getIndexSearcher() throws IOException{
        
        IndexReader indexReader=DirectoryReader.open(directory);
        
        IndexSearcher indexSearcher=new IndexSearcher(indexReader);
        
        return indexSearcher;
        
    }
    
    /**
     * 返回lucene 当前使用的版本信息...
     *
     * @return
     */
    public static Version getMatchVersion() {
        return matchVersion;
    }
    
    /**
     *
     *
     * 返回lucene 使用的分词器...
     * @return
     */
    public static Analyzer getAnalyzer() {
        return analyzer;
    }

}
