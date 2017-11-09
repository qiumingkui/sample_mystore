package com.mystore.common.persistence.jdbc;

public class Counter {
	private int i = 0;
	
	public int current(){
		return i;
	}
	
	public int next(){
		i++;
		return i;
	}

}
