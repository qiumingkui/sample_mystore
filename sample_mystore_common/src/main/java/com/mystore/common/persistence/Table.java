package com.mystore.common.persistence;

public abstract class Table<T> extends TableBase<T> {

	private static final long serialVersionUID = 1L;

	public Table() {
		super();
		init();
	}

	protected abstract void init();

	protected Column<T> add(String name, Column2PsValueSetter<T> psSetter, Rs2ColumnValueSetter<T> rsSetter) {
		Column<T> column = new Column<T>();
		column.setName(name);
		column.setPsSetter(psSetter);
		column.setRsSetter(rsSetter);
		map.put(column.name(), column);
		return column;
	}

	protected void setPrimaryKay(String columnName) {
		map.get(columnName).setPrimaryKay();
	}

	protected void setForeignKey(String columnName) {
		map.get(columnName).setForeignKey();
	}

	protected void setVersion(String columnName) {
		map.get(columnName).setVersion();
	}

}
