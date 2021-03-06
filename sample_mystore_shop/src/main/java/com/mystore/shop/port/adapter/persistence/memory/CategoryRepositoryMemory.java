//package com.mystore.shop.port.adapter.persistence.memory;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.mystore.shop.domain.model.category.Category;
//import com.mystore.shop.domain.model.category.CategoryId;
//import com.mystore.shop.domain.model.category.CategoryRepository;
//import com.mystore.shop.domain.model.category.Page;
//
////@Component
//public class CategoryRepositoryMemory implements CategoryRepository {
//
//	private static Map<CategoryId, Category> cache = new HashMap<CategoryId, Category>();
//
//	@Override
//	public Category get(CategoryId categoryId) {
//		return cache.get(categoryId);
//	}
//
//	@Override
//	public void create(Category category) {
//		cache.put(category.categoryId(), category);
//
//	}
//
//	@Override
//	public void update(Category category) {
//		cache.put(category.categoryId(), category);
//
//	}
//
//	@Override
//	public void delete(CategoryId categoryId) {
//		cache.remove(categoryId);
//
//	}
//
//	@Override
//	public List<Category> list() {
//	  Collection<Category> collection =	cache.values();
//	  List<Category> list = new ArrayList<Category>(collection);
//	  return list;
//	}
//
//	@Override
//	public Page<Category> page(int index, int size) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
