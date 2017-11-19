package com.mystore.shop.domain.model.category;

import java.util.ArrayList;

public class Page<T> extends ArrayList<T> {

	private static final long serialVersionUID = 1L;

	private static final long DEFAULT_SIZE = 1000;

	private static final long DEFAULT_START = 0;

	private static final long DEFAULT_COUNT = 0;

	private long start;

	private long end;

	private long size;

	private long count;

	public Page(long start, long size, long count) {
		super();
		this.start = start;
		this.end = start + size;
		this.size = size;
		this.count = count;

		calculate();
	}

	private void calculate() {

		if (count < DEFAULT_COUNT)
			count = DEFAULT_COUNT;

		if (start < DEFAULT_START)
			start = DEFAULT_START;

		if ((size < 0) || (size > DEFAULT_SIZE))
			size = DEFAULT_SIZE;

		end = start + size - 1;
	}

	public long getStart() {
		return start;
	}

	public long getEnd() {
		return end;
	}

	public long getSize() {
		return size;
	}

	public long getCount() {
		return count;
	}

}
