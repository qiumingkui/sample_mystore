/**
 *    Copyright 2010-2016 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.mystore.shop.domain.model.order;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.mystore.shop.domain.model.product.ProductItemId;

@Embeddable
public class SaleOrderItem implements Serializable {

	private static final long serialVersionUID = -1L;

	private int quantity;

	private BigDecimal unitPrice;

	private BigDecimal total;

	@Embedded
	@AttributeOverrides(@AttributeOverride(column = @Column(name = "product_item_id"), name = "id"))
	private ProductItemId productItemId;

	public SaleOrderItem() {
		super();
	}

	public SaleOrderItem(int quantity, BigDecimal unitPrice, BigDecimal total, ProductItemId productItemId) {
		super();
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.total = total;
		this.productItemId = productItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ProductItemId getProductItemId() {
		return productItemId;
	}

	public void setProductItemId(ProductItemId productItemId) {
		this.productItemId = productItemId;
	}

}
