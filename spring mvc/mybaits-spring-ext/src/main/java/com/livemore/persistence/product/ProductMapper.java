package com.livemore.persistence.product;

import java.util.List;

import com.livemore.model.product.Product;

public interface ProductMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Product record);

	int insertSelective(Product record);

	Product selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Product record);

	int updateByPrimaryKeyWithBLOBs(Product record);

	int updateByPrimaryKey(Product record);

	public List<Product> selectProductList(Product product);
	public Product selectProductById(long id);

	public List<Product> selectProductListByPage(Product product);
}