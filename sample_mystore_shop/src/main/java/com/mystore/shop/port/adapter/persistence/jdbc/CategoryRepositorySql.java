package com.mystore.shop.port.adapter.persistence.jdbc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.jdbc.sql.SQL;
import com.mystore.common.persistence.jdbc.sql.SelectContents;
import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryBase;
import com.mystore.shop.domain.model.category.CategoryFactory;
import com.mystore.shop.domain.model.category.CategoryId;
import com.mystore.shop.domain.model.category.CategoryRepository;
import com.mystore.shop.domain.model.category.CategoryTable;
import com.mystore.shop.port.adapter.persistence.repository.jdbc.EntitySqlProviderFactory;
import com.mystore.shop.port.adapter.persistence.repository.jdbc.DBUtiles;

@Component
public class CategoryRepositorySql implements CategoryRepository {
	private static final EntitySqlProviderFactory<CategoryBase> f = new EntitySqlProviderFactory<CategoryBase>();
	private static final CategoryTable t = new CategoryTable();

	@Autowired
	private DBUtiles<CategoryBase> dbUtiles;

	@Autowired
	private CategoryFactory categoryFactory;

	@Override
	public void create(Category category) {
		dbUtiles.operate(f.insertSqlProviderPair().getSqlProvider().provide(t),
				f.insertSqlProviderPair().getCollectionProvider().provide(t), (CategoryBase) category);
	}

	@Override
	public void update(Category category) {
		dbUtiles.operate(f.updateSqlProviderPair().getSqlProvider().provide(t),
				f.updateSqlProviderPair().getCollectionProvider().provide(t), (CategoryBase) category);
	}

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
