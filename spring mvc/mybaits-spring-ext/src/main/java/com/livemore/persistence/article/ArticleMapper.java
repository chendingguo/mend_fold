package com.livemore.persistence.article;

import java.util.List;

import com.livemore.model.article.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

	List<Article> selectArticleList(Article article);

	Article selectArticleById(Long id);

	List<Article> selectArticleListByPage(Article article);

	long selectTotalCount(Article article);
}