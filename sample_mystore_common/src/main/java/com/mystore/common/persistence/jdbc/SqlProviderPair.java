package com.mystore.common.persistence.jdbc;

public class SqlProviderPair<T> {

	private SqlProvider<T> sqlPrivader;
	
	private CollectionProvider<T> collectionProvider;

	public SqlProviderPair(SqlProvider<T> sqlProvider, CollectionProvider<T> collectionProvider) {
		super();
		this.sqlPrivader = sqlProvider;
		this.collectionProvider = collectionProvider;
	}

	public SqlProvider<T> getSqlProvider() {
		return sqlPrivader;
	}

	public CollectionProvider<T> getCollectionProvider() {
		return collectionProvider;
	}
	
	
}
