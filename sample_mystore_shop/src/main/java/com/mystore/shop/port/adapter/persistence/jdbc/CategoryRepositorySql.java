package com.mystore.shop.port.adapter.persistence.jdbc;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.Column;
import com.mystore.common.persistence.jdbc.Counter;
import com.mystore.common.persistence.jdbc.EntitySqlProviderFactory;
import com.mystore.common.persistence.jdbc.sql.InsertIntoContents;
import com.mystore.common.persistence.jdbc.sql.SQL;
import com.mystore.common.persistence.jdbc.sql.SelectContents;
import com.mystore.common.persistence.jdbc.sql.SetContents;
import com.mystore.common.persistence.jdbc.sql.ValuesContents;
import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryBase;
import com.mystore.shop.domain.model.category.CategoryFactory;
import com.mystore.shop.domain.model.category.CategoryId;
import com.mystore.shop.domain.model.category.CategoryRepository;
import com.mystore.shop.domain.model.category.CategoryTable;
import com.mystore.shop.port.adapter.persistence.repository.jdbc.DBUtiles;

@Component
public class CategoryRepositorySql implements CategoryRepository {
	private static final EntitySqlProviderFactory<CategoryBase> f = new EntitySqlProviderFactory<CategoryBase>();
	private static final CategoryTable t = new CategoryTable();

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DBUtiles<CategoryBase> dbUtiles;

	@Autowired
	private CategoryFactory categoryFactory;

	// @Override
	// public void update(Category category) {
	// dbUtiles.operate(f.updateSqlProviderPair().getSqlProvider().provide(t),
	// f.updateSqlProviderPair().getCollectionProvider().provide(t),
	// (CategoryBase) category);
	// }

	// @Override
	// public void update(Category category) {
	// Collection<Column<CategoryBase>> columns = filt(t.values(),
	// updateFilter());
	// String updateColumnParameters = updateColumnParameters(columns);
	// String sql = updateSql(t.name(), updateColumnParameters,
	// t.primaryKey().name());
	// jdbcTemplate.update(sql, pss(columns, (CategoryBase) category));
	// }

	// @Override
	// public void update(Category category) {
	// Collection<Column<CategoryBase>> columns = filt(t.values(),
	// updateFilter());
	//
	// String SQL = "UPDATE #{table} SET #{columnParameters} WHERE #{pk}=?}";
	// Collection<ReplaceObject> replaceObjects = new
	// ArrayList<ReplaceObject>();
	// replaceObjects.add(new ReplaceObject("table", t.name()));
	// replaceObjects.add(new ReplaceObject("columnParameters",
	// updateColumnParameters(columns)));
	// replaceObjects.add(new ReplaceObject("pk", t.primaryKey().name()));
	// SQL = sql(SQL, replaceObjects);
	// jdbcTemplate.update(SQL, pss(columns, (CategoryBase) category));
	// }

	@Override
	public void update(Category category) {
		Collection<Column<CategoryBase>> sqlColumns = filt(t.values(),
				(Collection<Column<CategoryBase>> target, Column<CategoryBase> column) -> {
					if ((!column.isPrimaryKay()) && (!column.isVersion()))
						target.add(column);
				});
		Collection<Column<CategoryBase>> pssColumns = new ArrayList<Column<CategoryBase>>(sqlColumns);
		pssColumns.add(t.primaryKey());

		String SQL = "UPDATE #{table} SET #{setContents} WHERE #{pk}=?";
		SQL = replace(SQL, "table", t.name());
		SQL = replace(SQL, "setContents", new SetContents<CategoryBase>(sqlColumns).toString());
		SQL = replace(SQL, "pk", t.primaryKey().name());

		jdbcTemplate.update(SQL, pss(pssColumns, (CategoryBase) category));
	}

	private String updateColumnParameters(Collection<Column<CategoryBase>> columns) {
		String string = null;
		for (Column<CategoryBase> column : columns) {
			string += (column.name() + "=?,");
		}
		string = string.substring(0, string.length() - 1);
		return string;
	}

	private String updateSql(String tableName, String columnParameters, String pkName) {
		String updateSql = "UPDATE #{tableName} SET #{columnParameters} WHERE #{pkName}=?}";
		updateSql = replace(updateSql, "tableName", tableName);
		updateSql = replace(updateSql, "columnParameters", columnParameters);
		updateSql = replace(updateSql, "pkName", pkName);
		return updateSql;
	}

	private String updateSqlWithVersion(String tableName, String columnParameters, String pkName, String versionName) {
		String updateSql = "UPDATE #{tableName} SET #{columnParameters},#{versionName}=#{versionName}+1 WHERE #{pkName}=? AND #{versionName}=?";
		updateSql = replace(updateSql, "tableName", tableName);
		updateSql = replace(updateSql, "columnParameters", columnParameters);
		updateSql = replace(updateSql, "pkName", pkName);
		updateSql = replace(updateSql, "versionName", versionName);
		return updateSql;
	}

	private String sql(String sql, Collection<ReplaceObject> replaceObjects) {
		String _sql = sql;
		for (ReplaceObject o : replaceObjects) {
			_sql = replace(_sql, o.getTarget(), o.getReplacement());
		}
		return _sql;
	}

	class ReplaceObject {
		String target;
		String replacement;

		public ReplaceObject(String target, String replacement) {
			super();
			this.target = target;
			this.replacement = replacement;
		}

		public String getTarget() {
			return target;
		}

		public String getReplacement() {
			return replacement;
		}

	}

	private String replace(String string, String target, String replacement) {
		String _string = string;
		_string = _string.replace("#{" + target + "}", replacement);
		return _string;
	}

	private Filter<CategoryBase> updateFilter() {
		Filter<CategoryBase> filter = (Collection<Column<CategoryBase>> target, Column<CategoryBase> column) -> {
			if (column.isPrimaryKay())
				return;
			if (column.isVersion())
				return;
			target.add(column);
		};
		return filter;
	}

	private Collection<Column<CategoryBase>> filt(Collection<Column<CategoryBase>> source,
			Filter<CategoryBase> filter) {
		Collection<Column<CategoryBase>> target = new ArrayList<Column<CategoryBase>>();
		for (Column<CategoryBase> column : source) {
			filter.doFilt(target, column);
		}
		return target;
	}

	interface Filter<CategoryBase> {
		void doFilt(Collection<Column<CategoryBase>> target, Column<CategoryBase> column);
	}

	// @Override
	// public void create(Category category) {
	// dbUtiles.operate(f.insertSqlProviderPair().getSqlProvider().provide(t),
	// f.insertSqlProviderPair().getCollectionProvider().provide(t),
	// (CategoryBase) category);
	// }

	@Override
	public void create(Category category) {
		Collection<Column<CategoryBase>> columns = t.values();
		jdbcTemplate.update(insertSql(t.name(), columns), pss(columns, (CategoryBase) category));
	}

	private String insertSql(String tableName, Collection<Column<CategoryBase>> columns) {
		InsertIntoContents<CategoryBase> insertIntoContents = new InsertIntoContents<CategoryBase>(tableName, columns);
		ValuesContents<CategoryBase> valuesContents = new ValuesContents<CategoryBase>(columns);
		String insertSql = new SQL().INSERT_INTO(insertIntoContents.toString()).VALUES(valuesContents.toString())
				.toString();
		return insertSql;
	}

	private PreparedStatementSetter pss(Collection<Column<CategoryBase>> columns, CategoryBase categoryBase) {
		PreparedStatementSetter setter = (PreparedStatement ps) -> {
			Counter counter = new Counter();
			for (Column<CategoryBase> column : columns) {
				try {
					column.fill(ps, counter.next(), categoryBase);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		return setter;
	}

	// @Override
	// public void update(Category category) {
	// dbUtiles.operate(f.updateSqlProviderPair().getSqlProvider().provide(t),
	// f.updateSqlProviderPair().getCollectionProvider().provide(t),
	// (CategoryBase) category);
	// }

	public void delete(CategoryBase category) {
		dbUtiles.operate(f.deleteSqlProviderPair().getSqlProvider().provide(t),
				f.deleteSqlProviderPair().getCollectionProvider().provide(t), category);
	}

	@Override
	public void delete(CategoryId categoryId) {
		dbUtiles.operate(f.deleteSqlProviderPair().getSqlProvider().provide(t), categoryId.id());
	}

	@Override
	public Category get(CategoryId categoryId) throws Exception {
		List<CategoryBase> list = dbUtiles.query(CategoryBase.class,
				f.selectSqlProviderPair().getSqlProvider().provide(t),
				f.selectSqlProviderPair().getCollectionProvider().provide(t), new Object[] { categoryId.id() });
		return list.size() > 0 ? categoryFactory.category(list.get(0)) : null;
	}

	@Override
	public List<Category> getCategoryList() throws Exception {
		SQL ql = new SQL();
		SelectContents<CategoryBase> selectContents = new SelectContents<CategoryBase>(t.values());
		List<CategoryBase> list = dbUtiles.query(CategoryBase.class,
				ql.SELECT(selectContents.toString()).FROM(t.name()).toString(), t.values(), new Object[] {});
		return categoryFactory.categoryList(list);
	}
}
