package com.livemore.action;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mybatis.page.Page;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.livemore.model.Picture;
import com.livemore.model.product.Product;
import com.livemore.service.PictureService;
import com.livemore.service.product.ProductService;
import com.livemore.utils.CommonUtils;

/**
 * 获取图片
 * 
 * @author Administrator
 *
 */

@Controller
@Path(value = "picture")
public class PictureAction {
	Logger logger = Logger.getLogger(getClass());
	private String uploadPath = "upload_file/"; // 上传文件的目录
	private String tempPath = "uploadtmp/"; // 临时文件目录
	private String[] fileType = new String[] { ".jpg", ".gif", ".bmp", ".png",
			".jpeg", ".ico" };
	private int sizeMax = 5;// 图片最大上限

	@Autowired
	private PictureService pictureService;

	@Autowired
	private ProductService productService;

	@GET
	@Path("selectPictureList")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getPictureList(@Context HttpServletRequest request)
			throws IOException, URISyntaxException {
		Picture picture = new Picture();
		picture.setSortColumn("ID DESC");
		List<Picture> pictureList = pictureService.selectPictureList(picture);
		for (Picture pic : pictureList) {
			long productId = pic.getProductId();
			Product product = productService.selectByPrimaryKey(productId);
			if (product != null) {
				pic.setName(product.getName());
			}

		}
		return Response.ok(pictureList).build();

	}

	@GET
	@Path("/removePicture/{id}")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response removePicture(@Context HttpServletRequest request,
			@PathParam("id") Long id) throws IOException, URISyntaxException {
		int result = pictureService.deleteByPrimaryKey(id);
		return Response.ok(result).build();

	}

	/**
	 * update picture
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@GET
	@Path("/updatePicture")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updatePicture(@Context HttpServletRequest request,
			@QueryParam("id") Long id, @QueryParam("remark") String remark,@QueryParam("orderNum") int orderNum) {
		if (id != null && remark != null) {
			Picture picture = new Picture();
			picture.setId(id);
			picture.setRemark(remark);
			picture.setOrderNum(orderNum);
			int result = pictureService.updateByPrimaryKeySelective(picture);
			return Response.ok(result).build();

		}
		return Response.ok(-1).build();

	}

	/**
	 * delete pictures by is array
	 */
	@GET
	@Path("/removePictures")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response removePictures(@Context HttpServletRequest request,
			@QueryParam("ids") String ids) {
		try {
			if (ids != null && !ids.isEmpty()) {
				String[] idArray = ids.split(",");
				for (String idStr : idArray) {
					long id = Long.parseLong(idStr);
					pictureService.deleteByPrimaryKey(id);
				}

			}
		} catch (Exception e) {
			logger.error(e);
			return Response.ok(-1).build();
		}

		return Response.ok(1).build();

	}

	/**
	 * set the picture cover
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@GET
	@Path("setAsCover")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response setAsCover(@Context HttpServletRequest request,
			@QueryParam("ids") String ids) throws IOException,
			URISyntaxException {
		if (ids != null && !ids.isEmpty()) {
			String[] idArray = ids.split(",");
			for (String idStr : idArray) {
				long id = Long.parseLong(idStr);
				Picture picture = pictureService.selectByPrimaryKey(id);
				long productId = picture.getProductId();
				// search the old cover set status=0
				Picture pictureParam = new Picture();
				pictureParam.setProductId(productId);
				pictureParam.setStatus(1);
				List<Picture> pictureList = pictureService
						.selectPictureList(pictureParam);
				for (Picture pic : pictureList) {
					pic.setStatus(0);
					pictureService.updateByPrimaryKeySelective(pic);

				}
				// set default cover
				Picture pictureBean = new Picture();
				pictureBean.setId(id);
				pictureBean.setStatus(1);
				pictureService.updateByPrimaryKeySelective(pictureBean);

			}
			return Response.ok(1).build();
		} else {
			return Response.ok(-1).build();
		}

	}

	@GET
	@Path("/selectPictureListByPage")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response selectPictureListByPage(
			@Context HttpServletRequest request, @QueryParam("id") long id,
			@QueryParam("page") int page) throws IOException,
			URISyntaxException {
		Map<String, Object> map = new HashMap<String, Object>();
		Page pager = new Page();

		if (page == 0) {
			page = 1;
		}

		pager.setCurrentPage(page);

		Picture picture = new Picture();
		picture.setSortColumn("ID DESC");
		picture.setPage(pager);
		// ==============search conditions=================
		if (id > 0) {
			picture.setId(id);
		}
		List<Picture> pictureList = pictureService
				.selectPictureListByPage(picture);
		for (Picture pic : pictureList) {
			long productId = pic.getProductId();
			Product product = productService.selectByPrimaryKey(productId);
			if (product != null) {
				pic.setName(product.getName());
			}

		}
		Picture pictureParam = new Picture();
		long totalCount = pictureService.selectTotalCount(pictureParam);

		map.put("rows", pictureList);
		map.put("total", totalCount);
		return Response.ok(map).build();

	}

	@POST
	@Path("savePicture")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response post(@Context HttpServletRequest request)
			throws IOException, URISyntaxException {
		logger.error("save pictures");
		String serverPath = CommonUtils.getProperties("img.upload.folder");
		if (!new File(serverPath).isDirectory()) {
			new File(serverPath + uploadPath).mkdirs();
		}

		if (!new File(serverPath).isDirectory()) {
			new File(serverPath + tempPath).mkdirs();
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(sizeMax * 1024 * 1024); // 最大缓存
		factory.setRepository(new File(serverPath + tempPath));

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(sizeMax * 1024 * 1024);// 文件最大上限
		String filePath = null;
		HashMap<String, Object> filesMap = new HashMap<String, Object>();
		List<HashMap> fileInfoList = new ArrayList<HashMap>();
		Picture picture = new Picture();
		try {

			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);
			//
			for (int i = 0; i < items.size(); i++) {
				// 里面一个for循环，获取一行的数据
				FileItem item = items.get(i);
				if (!item.isFormField()) {// 文件名
					String fileName = item.getName().toLowerCase();
					if (fileName.endsWith(fileType[0])
							|| fileName.endsWith(fileType[1])
							|| fileName.endsWith(fileType[2])
							|| fileName.endsWith(fileType[3])
							|| fileName.endsWith(fileType[4])
							|| fileName.endsWith(fileType[5])) {

						filePath = serverPath + uploadPath + fileName;
						// System.out.println(filePath);
						File file = new File(filePath);
						item.write(file);

						picture.setPath(uploadPath + fileName);
						System.out.println(fileName);
						HashMap<String, String> fileMap = new HashMap<String, String>();
						fileMap.put("thumbnailUrl", fileName);
						fileMap.put("url", filePath);
						fileMap.put("name", fileName);
						fileInfoList.add(fileMap);
					} else {
						request.setAttribute("errorMsg",
								"上传失败,请确认上传的文件存在并且类型是图片!");

					}
				} else {
					// 非文件流
					String fieldName = item.getFieldName();
					String value = item.getString();
					value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
					System.out.println(fieldName + ":" + value);

					if ("productId".equals(fieldName)) {
						Long productId = Long.parseLong(value);
						picture.setProductId(productId);
					}

					if ("remark".equals(fieldName)) {

						picture.setRemark(value);
					}

				}

			}

			pictureService.insert(picture);

			filesMap.put("files", fileInfoList);
			filesMap.put("pictureId", picture.getId());

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "上传失败,请确认上传的文件存在并且类型是图片!");

		}

		return Response.ok(filesMap).build();
	}

	@GET
	@Path("savePictureRemark")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response savePictureRemark(@Context HttpServletRequest request,
			@QueryParam("id") long id, @QueryParam("remark") String remark) {
		Picture picture = new Picture();
		picture.setId(id);
		picture.setRemark(remark);
		int result = pictureService.updateByPrimaryKeySelective(picture);
		return Response.ok(result).build();

	}

}