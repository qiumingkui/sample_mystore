package com.mystore.shop.application;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryId;
import com.mystore.shop.domain.model.category.CategoryRepository;

@Service
public class CatalogService {

	@Autowired
	private CategoryRepository _categoryRepository;

	public List<Category> getCategoryList() throws Exception {
		return _categoryRepository.getCategoryList();
	}

	public Category getCategory(long categoryId) throws Exception {
		CategoryId _categoryId = new CategoryId(categoryId);
		return _categoryRepository.get(_categoryId);
	}

	// public Product getProduct(String productId) {
	// return productMapper.getProduct(productId);
	// }
	//
	// public List<Product> getProductListByCategory(String categoryId) {
	// return productMapper.getProductListByCategory(categoryId);
	// }
	//
	// public List<Product> searchProductList(String keywords) {
	// List<Product> products = new ArrayList<Product>();
	// for(String keyword : keywords.split("\\s+")){
	// products.addAll(productMapper.searchProductList("%" +
	// keyword.toLowerCase() + "%"));
	// }
	// return products;
	// }
	//
	// public List<Item> getItemListByProduct(String productId) {
	// return itemMapper.getItemListByProduct(productId);
	// }
	//
	// public Item getItem(String itemId) {
	// return itemMapper.getItem(itemId);
	// }
	//
	// public boolean isItemInStock(String itemId) {
	// return itemMapper.getInventoryQuantity(itemId) > 0;
	// }
}