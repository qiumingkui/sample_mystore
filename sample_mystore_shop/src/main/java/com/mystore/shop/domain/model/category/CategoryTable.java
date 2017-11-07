package com.mystore.shop.domain.model.category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mystore.common.persistence.Column;
import com.mystore.common.persistence.Table;

public class CategoryTable extends Table<Category> {

	private static final long serialVersionUID = 1L;

	public CategoryTable() {
		super();
		init();
	}

	private void init() {
		this.changeName("category");

		Column<Category> idColumn = new Column<Category>();
		idColumn.changeName("id");
		idColumn.changePreparedStatementParameteSetter(
				(PreparedStatement ps, int index, Category category) -> ps.setLong(index, category.categoryId().id()));
		// idColumn.changeResultSetSetter(
		// (Category object, ResultSet rs) ->
		// object.changeXX(rs.getString(idColumn.name())));
		idColumn.setPrimaryKay();
		this.put(idColumn.name(), idColumn);

		Column<Category> nameColumn = new Column<Category>();
		nameColumn.changeName("name");
		nameColumn.changePreparedStatementParameteSetter(
				(PreparedStatement ps, int index, Category category) -> ps.setString(index, category.name()));
		nameColumn.changeResultSetSetter(
				(Category category, ResultSet rs) -> category.changeName(rs.getString(nameColumn.name())));
		this.put(nameColumn.name(), nameColumn);

		Column<Category> descriptionColumn = new Column<Category>();
		descriptionColumn.changeName("description");
		descriptionColumn.changePreparedStatementParameteSetter(
				(PreparedStatement ps, int index, Category category) -> ps.setString(index, category.description()));
		descriptionColumn.changeResultSetSetter((Category category, ResultSet rs) -> category
				.changeDescription(rs.getString(descriptionColumn.name())));
		this.put(descriptionColumn.name(), descriptionColumn);
	}
}
