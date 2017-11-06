package com.mystore.common.persistence.jdbc.sql;

public class SQL {
	private String sql = "";

	public SQL SELECT(String sql) {
		return attach(new Select(sql).toString());
	}

	public SQL FROM(String sql) {
		return attach(new From(sql).toString());
	}

	public SQL WHERE(String sql) {
		return attach(new Where(sql).toString());
	}
	
	public SQL UPDATE(String sql) {
		return attach(new Update(sql).toString());
	}
	public SQL SET(String sql) {
		return attach(new Set(sql).toString());
	}

	public SQL INSERT(String sql) {
		return attach(new Insert(sql).toString());
	}

	public SQL INTO(String sql) {
		return attach(new Into(sql).toString());
	}

	public SQL VALUES(String sql) {
		return attach(new Values(sql).toString());
	}

	public SQL DELETE(String sql) {
		return attach(new Delete(sql).toString());
	}

	public String toString(){
		return sql;
	}
	
	protected SQL attach(String sql){
		this.sql += sql;
		return this;
	}
}
