package com.jevon.dao;

import com.jevon.entity.Article;
import com.jevon.entity.Comment;

import java.util.List;
import java.util.Map;



public interface DiscDaoMapper {
    //DaoMapper用于JDBC访问数据库

    //通过id访问文章
    Article selectArticleById(String id);

    //获取全部文章
    List<Article> getAllArticles();

    //通过评论id访问评论
    Comment selectCommentById(String id);

    //根据文章id查找评论
    List<Comment> getAllCommentByID(String id);

    //添加文章
    int addArticle(Map<String,Object> map);

    //添加评论
    int addComment(Map<String,Object> map);

    //删评论,注意是评论的id
    int deleteComment(String id);

    //删文章，文章的id
    int deleteArticle(String id);

    int AddArticleReadNum(String article_id);

    int AddArticleCommentNum(String article_id);

    int SubArticleCommentNum(String article_id);
 
}
