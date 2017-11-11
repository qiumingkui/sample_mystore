package com.mystore.shop.port.adapter.persistence.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mystore.shop.domain.model.product.Product;
import com.mystore.shop.domain.model.product.ProductBase;
import com.mystore.shop.domain.model.product.ProductFactory;
import com.mystore.shop.domain.model.product.ProductId;
import com.mystore.shop.domain.model.product.ProductRepository;

@Component
public class ProductRepositorySql implements ProductRepository {

	@Autowired
	private ProductFactory productFactory;

	@Autowired
	private ProductBaseSql productBaseSql;

	@Override
	public void create(Product product) {
		productBaseSql.insert((ProductBase) product);
	}

	@Override
	public void update(Product product) {
		productBaseSql.update((ProductBase) product);
	}

	@Override
	public void delete(ProductId productId) {
		productBaseSql.deleteById(productId);
	}

	@Override
	public List<Product> getList() throws Exception {
		List<ProductBase> productBaseList = productBaseSql.findAll();
		return productFactory.productList(productBaseList);
	}

	@Override
	public Product get(ProductId productId) throws Exception {
		ProductBase productBase = productBaseSql.findOneById(productId);
		return productFactory.product(productBase);
	}

}
