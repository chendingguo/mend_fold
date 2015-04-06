package com.livemore.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livemore.model.product.Product;
import com.livemore.persistence.product.ProductMapper;

@Service
public class ProductService {
	@Autowired
	private ProductMapper productMapper;

	public int insert(Product product) {
		return productMapper.insert(product);

	}

	public Product selectByPrimaryKey(Long id) {
		return productMapper.selectByPrimaryKey(id);
	}

	public List<Product> selectProductList(Product product) {
		return productMapper.selectProductList(product);
	}

	public Product selectProductById(Long id) {
		return productMapper.selectProductById(id);
	}

	public int deleteByPrimaryKey(Long id) {
		return productMapper.deleteByPrimaryKey(id);
	}

	public List<Product> selectProductListByPage(Product product) {
		return productMapper.selectProductListByPage(product);
	}

	public int updateByPrimaryKeySelective(Product product) {
		return productMapper.updateByPrimaryKeySelective(product);
	}
}
