package com.mystore.shop.application;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryId;
import com.mystore.shop.domain.model.category.CategoryRepository;
import com.mystore.shop.domain.model.order.SaleOrder;
import com.mystore.shop.domain.model.order.SaleOrderId;
import com.mystore.shop.domain.model.order.SaleOrderRepository;

@Service
public class CatalogService {

	@Autowired
	private CategoryRepository _categoryRepository;

	@Autowired
	private SaleOrderRepository _saleOrderRepository;

	public List<Category> getCategoryList() throws Exception {
		return _categoryRepository.list();
	}

	public Category getCategory(long categoryId) throws Exception {
		CategoryId _categoryId = new CategoryId(categoryId);
		return _categoryRepository.get(_categoryId);
	}

	// public List<SaleOrder> listSaleOrder(){
	// return _saleOrderRepository.
	// }
	
	public void requestPeyout(long saleOrderId) {
		SaleOrder saleOrder = _saleOrderRepository.get(new SaleOrderId(saleOrderId));
		saleOrder.requestPayout();
		_saleOrderRepository.update(saleOrder);

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