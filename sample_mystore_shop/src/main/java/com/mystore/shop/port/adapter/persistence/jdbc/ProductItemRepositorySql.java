package com.mystore.shop.port.adapter.persistence.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mystore.shop.domain.model.product.ProductId;
import com.mystore.shop.domain.model.productitem.ProductItem;
import com.mystore.shop.domain.model.productitem.ProductItemBase;
import com.mystore.shop.domain.model.productitem.ProductItemFactory;
import com.mystore.shop.domain.model.productitem.ProductItemId;
import com.mystore.shop.domain.model.productitem.ProductItemRepository;

@Component
public class ProductItemRepositorySql implements ProductItemRepository {

	@Autowired
	private ProductItemBaseSql productItemBaseSql;

	@Autowired
	private ProductItemFactory productItemFactory;

	@Override
	public void create(ProductItem productItem) {
		productItemBaseSql.insert((ProductItemBase) productItem);
	}

	@Override
	public void update(ProductItem productItem) {
		productItemBaseSql.update((ProductItemBase) productItem);
	}

	@Override
	public void delete(ProductItemId productItemId) {
		productItemBaseSql.deleteById(productItemId);
	}

	@Override
	public ProductItem get(ProductItemId productItemId) throws Exception {
		ProductItemBase productItemBase = productItemBaseSql.findOneById(productItemId);
		return productItemFactory.productItem(productItemBase);
	}

	@Override
	public List<ProductItem> getList() throws Exception {
		List<ProductItemBase> productItemBases = productItemBaseSql.findAll();
		return productItemFactory.productItemList(productItemBases);
	}

	@Override
	public List<ProductItem> getListByProductId(ProductId productId) throws Exception {
		List<ProductItemBase> productItemBases = productItemBaseSql.findAllByProductId(productId);
		return productItemFactory.productItemList(productItemBases);
	}

}
