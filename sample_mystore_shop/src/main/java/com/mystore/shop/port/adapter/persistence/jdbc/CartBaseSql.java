package com.mystore.shop.port.adapter.persistence.jdbc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.Column;
import com.mystore.common.persistence.jdbc.JdbcEntityDao;
import com.mystore.shop.domain.model.cart.CartBase;
import com.mystore.shop.domain.model.cart.CartId;
import com.mystore.shop.domain.model.customer.CustomerId;

@Component
public class CartBaseSql extends JdbcEntityDao<CartBase, CartId> {

	public List<CartBase> findAllByCustomerId(CustomerId customerId) {
		List<CartId> cartIds = findAllIdByCustomerId(customerId);
		List<CartBase> cartBases = findAll(cartIds);
		return cartBases;
	}

	public List<CartId> findAllIdByCustomerId(CustomerId customerId) {
		Collection<Column<CartBase>> rsColumns = new ArrayList<Column<CartBase>>();
		rsColumns.add(table.primaryKey());

		String SQL = "SELECT #{pk} FROM #{table} WHERE #{customerid}=?";
		SQL = sqlSetting(SQL, "table", table.name());
		SQL = sqlSetting(SQL, "pk", table.primaryKey().name());
		SQL = sqlSetting(SQL, "customerid", table.column(CartTable.CUSTOMERID).name());

		List<CartBase> objectWithKeyList = jdbcTemplate.query(SQL, new Object[] { customerId.getId() },
				provideRowMapper(rsColumns));

		return fetchIdList(objectWithKeyList);
	}

	@Autowired
	@Override
	protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	protected void init() {
		this.table = new CartTable();
	}

	@Override
	protected CartBase produceObject(CartId key) {
		CartBase cartBase = produceObject();
		cartBase.setCartId(key);
		return cartBase;
	}

	@Override
	protected CartBase produceObject() {
		CartBase cartBase = new CartBase();
		return cartBase;
	}

	@Override
	protected CartId fetchID(CartBase object) {
		CartId cartId = object.getCartId();
		return cartId;
	}

}
