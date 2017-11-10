package com.mystore.shop.domain.model.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.mystore.shop.domain.model.category.CategoryId;
import com.mystore.shop.domain.model.product.Product;
import com.mystore.shop.domain.model.product.ProductBase;
import com.mystore.shop.domain.model.product.ProductId;
import com.mystore.shop.domain.model.product.ProductModel;

@Component
public class ProductFactory {

	public Product product(ProductId productId, CategoryId categoryId, String name, String description) {
		// ProductId _productId = new ProductId(productId);
		// ProductId _productId = new ProductId(productId);
		Product _product = new ProductModel(productId, categoryId, name, description);
		return _product;
	}

	@SuppressWarnings("unused")
	public Product product(ProductBase productBase) {
		ProductModel productModel = new ProductModel();
		if (productBase == null)
			return null;
		BeanUtils.copyProperties(productBase, productModel);
		return productModel;
	}

	public List<Product> productList(List<ProductBase> productBaseList) {
		List<Product> productList = new ArrayList<Product>();
		for (ProductBase base : productBaseList) {
			productList.add(product(base));
		}
		return productList;
	}

}