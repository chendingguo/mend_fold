package com.livemore.service.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livemore.model.article.Article;
import com.livemore.persistence.article.ArticleMapper;

@Service
public class ArticleService {
	@Autowired
	private ArticleMapper articleMapper;

	public int insert(Article article) {
		return articleMapper.insert(article);

	}

	public Article selectByPrimaryKey(Long id) {
		return articleMapper.selectByPrimaryKey(id);
	}

	public List<Article> selectArticleList(Article article) {
		return articleMapper.selectArticleList(article);
	}

	public Article selectArticleById(Long id) {
		return articleMapper.selectArticleById(id);
	}

	public int deleteByPrimaryKey(Long id) {
		return articleMapper.deleteByPrimaryKey(id);
	}

	public List<Article> selectArticleListByPage(Article article) {
		return articleMapper.selectArticleListByPage(article);
	}

	public int updateByPrimaryKeySelective(Article article) {
		return articleMapper.updateByPrimaryKeySelective(article);
	}

	public long selectTotalCount(Article article) {
		return articleMapper.selectTotalCount(article);
		
	}
}
