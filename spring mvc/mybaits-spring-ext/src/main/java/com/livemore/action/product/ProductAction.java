package com.livemore.action.product;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mybatis.page.Page;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.livemore.model.Picture;
import com.livemore.model.product.Product;
import com.livemore.service.PictureService;
import com.livemore.service.product.ProductService;
import com.livemore.utils.CommonUtils;

@Controller
@Path(value = "product")
public class ProductAction {
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ProductService productService;
	@Autowired
	private PictureService pictureService;

	@GET
	@Path("saveProduct")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response post(@Context HttpServletRequest request) throws Exception {

		Product product = new Product();

		if (request.getParameter("name") != null) {
			product.setName(request.getParameter("name"));
		}
		if (request.getParameter("price") != null) {
			double price=Double.parseDouble(request.getParameter("price"));
			product.setPrice(price);
		}
		if (request.getParameter("categoryId") != null) {
			long categoryId = Long
					.parseLong(request.getParameter("categoryId"));
			product.setCategoryId(categoryId);
			String categoryName = CommonUtils.getCategoryName(categoryId);
			product.setCategoryName(categoryName);

		}
		if (request.getParameter("introduce") != null) {
			product.setIntroduce(request.getParameter("introduce"));
		}
		
		if (request.getParameter("remark") != null) {
			product.setRemark(request.getParameter("remark"));
		}

		int result = productService.insert(product);

		return Response.ok(result).build();
	}

	@GET
	@Path("selectProductList")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProductList(@Context HttpServletRequest request,
			@QueryParam("categoryId") long categoryId) {

		Product product = new Product();
		if (categoryId != 0) {
			product.setCategoryId(categoryId);
		}
		product.setSortColumn("ID DESC");
		List<Product> ProductList = productService.selectProductList(product);

		return Response.ok(ProductList).build();

	}

	@GET
	@Path("/removeProduct/{id}")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response removeProduct(@Context HttpServletRequest request,
			@PathParam("id") Long id) throws IOException, URISyntaxException {
		int result = productService.deleteByPrimaryKey(id);
		// delete picture
		Picture picture = new Picture();
		picture.setProductId(id);
		;
		List<Picture> pictureList = pictureService.selectPictureList(picture);
		if (pictureList != null) {
			for (Picture pic : pictureList) {
				pictureService.deleteByPrimaryKey(pic.getId());
			}
		}
		return Response.ok(result).build();

	}

	/**
	 * update product
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@GET
	@Path("/updateProduct")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateProduct(@Context HttpServletRequest request,
			@QueryParam("id") long id,
			@QueryParam("price") double price,
			@QueryParam("categoryId") long categoryId,
			@QueryParam("name") String name,
			@QueryParam("introduce") String introduce,@QueryParam("remark") String remark) {

		if (id > 0) {
			Product product = new Product();
			product.setId(id);
			if (categoryId > 0) {
				product.setCategoryId(categoryId);
				product.setCategoryName(CommonUtils.getCategoryName(categoryId));
			}
			
			if (price > 0) {
				product.setPrice(price);
			}
			if (!name.isEmpty()) {
				product.setName(name);
			}
			if (!introduce.isEmpty()) {
				product.setIntroduce(introduce);
			}
			
			if (!remark.isEmpty()) {
				product.setRemark(remark);
			}
			productService.updateByPrimaryKeySelective(product);
			return Response.ok(1).build();
		} else {
			logger.error("id is null or zero");
			return Response.ok(-1).build();
		}

	}

	@GET
	@Path("/selectProductListByPage")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response selectProductListByPage(
			@Context HttpServletRequest request,
			@QueryParam("categoryId") long categoryId) throws IOException,
			URISyntaxException {

		String currentPageStr = request.getParameter("currentPage");
		Page page = new Page();

		int currentPage = 1;

		if (currentPageStr != null) {
			currentPage = Integer.parseInt(currentPageStr);
			page.setCurrentPage(currentPage);
		}

		Product product = new Product();
		if (categoryId > 0) {
			product.setCategoryId(categoryId);
		}
		product.setSortColumn("ID DESC");
		product.setPage(page);

		List<Product> productList = productService
				.selectProductListByPage(product);
		if (productList != null && productList.size() > 0) {
			// find the pictures of this product
			for (Product pro : productList) {
				Picture picture = new Picture();
				picture.setProductId(pro.getId());
				picture.setStatus(1);
				picture.setSortColumn("ID DESC");
				List<Picture> pictureList = pictureService
						.selectPictureList(picture);
				pro.setPictureList(pictureList);

			}
		}

		return Response.ok(productList).build();

	}

	@GET
	@Path("/selectProductById/{id}")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response selectProductById(@Context HttpServletRequest request,
			@PathParam("id") Long id) throws IOException, URISyntaxException {

		Product product = productService.selectProductById(id);
		if(product!=null){
			Picture picture=new Picture();
			picture.setProductId(product.getId());
			picture.setSortColumn("ORDER_NUM");
			List<Picture> pictureList = pictureService.selectPictureList(picture);
			product.setPictureList(pictureList);
		}
		return Response.ok(product).build();

	}

}