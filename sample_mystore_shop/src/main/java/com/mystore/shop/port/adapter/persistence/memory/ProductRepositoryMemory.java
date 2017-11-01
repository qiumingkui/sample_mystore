package com.mystore.shop.port.adapter.persistence.memory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import com.mystore.shop.domain.model.product.Product;
import com.mystore.shop.domain.model.product.ProductId;
import com.mystore.shop.domain.model.product.ProductRepository;

@Component
public class ProductRepositoryMemory implements ProductRepository {

	private static Map<ProductId, Product> cache = new HashMap<ProductId, Product>();

	@Override
	public Product get(ProductId productId) {
		return cache.get(productId);
	}

	@Override
	public void create(Product product) {
		cache.put(product.productId(), product);

	}

	@Override
	public void update(Product product) {
		cache.put(product.productId(), product);

	}

	@Override
	public void delete(ProductId productId) {
		cache.remove(productId);

	}
}
