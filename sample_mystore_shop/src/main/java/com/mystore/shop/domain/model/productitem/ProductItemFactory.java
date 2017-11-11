package com.mystore.shop.domain.model.productitem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.mystore.shop.domain.model.product.ProductId;

@Component
public class ProductItemFactory {

	public ProductItem productItem(ProductItemId productItemId, ProductId productId, BigDecimal listPrice,
			BigDecimal unitCost, int quantity) {
		ProductItem productItem = new ProductItemModel(productItemId, productId, listPrice, unitCost, quantity);
		return productItem;
	}
	
	public ProductItem productItem(ProductItemBase productItemBase) {
		ProductItemModel productItemModel = new ProductItemModel();
		if (productItemBase == null)
			return null;
		BeanUtils.copyProperties(productItemBase, productItemModel);
		return productItemModel;
	}

	public List<ProductItem> productItemList(List<ProductItemBase> productItemBaseList) {
		List<ProductItem> productItemList = new ArrayList<ProductItem>();
		for (ProductItemBase base : productItemBaseList) {
			productItemList.add(productItem(base));
		}
		return productItemList;
	}

}
