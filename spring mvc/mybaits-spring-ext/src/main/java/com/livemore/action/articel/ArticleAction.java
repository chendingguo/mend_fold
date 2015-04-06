package com.livemore.action.articel;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mybatis.page.Page;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.livemore.model.article.Article;
import com.livemore.service.article.ArticleService;

@Controller
@Path(value = "article")
public class ArticleAction {
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ArticleService articleService;

	@POST
	@Path("saveArticle")
	public Response saveArticle(@Context HttpServletRequest request,
			@FormParam(value = "title") String title,
			@FormParam(value = "htmlContent") String htmlContent,
			@FormParam(value = "categoryId") int categoryId) throws Exception {
		Article article = new Article();
		article.setCategoryId(categoryId);
		article.setTitle(title);
		article.setHtmlContent(htmlContent);
		article.setCreaterTime(new Date());
		int result = articleService.insert(article);

		return Response.ok(result).build();
	}

	@POST
	@Path("updateArticle")
	public Response upadateArticle(@Context HttpServletRequest request,
			@FormParam(value = "id") long id,
			@FormParam(value = "title") String title,
			@FormParam(value = "htmlContent") String htmlContent,
			@FormParam(value = "categoryId") int categoryId) throws Exception {
		Article article = new Article();
		article.setId(id);
		article.setCategoryId(categoryId);

		article.setTitle(title);
		article.setHtmlContent(htmlContent);
		article.setCreaterTime(new Date());
		int result = articleService.updateByPrimaryKeySelective(article);

		return Response.ok(result).build();
	}

	/**
	 * delete articles by is array
	 */
	@GET
	@Path("/removeArticles")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response removeArticles(@Context HttpServletRequest request,
			@QueryParam("ids") String ids) {
		try {
			if (ids != null && !ids.isEmpty()) {
				String[] idArray = ids.split(",");
				for (String idStr : idArray) {
					long id = Long.parseLong(idStr);
					articleService.deleteByPrimaryKey(id);
				}

			}
		} catch (Exception e) {
			logger.error(e);
			return Response.ok(-1).build();
		}

		return Response.ok(1).build();

	}

	@GET
	@Path("/selectArticleListByPage")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response selectProductListByPage(
			@Context HttpServletRequest request,
			@QueryParam("categoryId") int categoryId) throws IOException,
			URISyntaxException {
		Map<String, Object> map = new HashMap<String, Object>();

		String currentPageStr = request.getParameter("currentPage");
		Page page = new Page();

		int currentPage = 1;

		if (currentPageStr != null) {
			currentPage = Integer.parseInt(currentPageStr);
			page.setCurrentPage(currentPage);
		}

		Article article = new Article();

		if (categoryId > 0) {
			article.setCategoryId(categoryId);
		}
		article.setSortColumn("ID DESC");
		article.setPage(page);
		
		List<Article> articleList = articleService
				.selectArticleListByPage(article);
		
		Article articleParam = new Article();
		long totalCount = articleService.selectTotalCount(articleParam);

		map.put("rows", articleList);
		map.put("total", totalCount);

		return Response.ok(map).build();

	}

	@GET
	@Path("/selectArticleListById")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response selectArticleListById(@Context HttpServletRequest request,
			@QueryParam("id") long id) throws IOException, URISyntaxException {

		Article article = articleService.selectByPrimaryKey(id);

		return Response.ok(article).build();

	}
}