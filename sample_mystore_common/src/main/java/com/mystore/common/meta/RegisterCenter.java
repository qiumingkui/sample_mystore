package com.mystore.common.meta;

import com.mystore.common.meta.domain.AggregateRootMetaRegister;
import com.mystore.common.meta.domain.EntityMetaRegister;
import com.mystore.common.meta.domain.ValueObjectMetaRegister;

public class RegisterCenter {

	private static Object lock = new Object();

	private static RegisterCenter single;

	private ClassMetaRegister classMetaRegister = new ClassMetaRegister();

	private EntityMetaRegister entityMetaRegister = new EntityMetaRegister();

	private AggregateRootMetaRegister aggregateRootMetaRegister = new AggregateRootMetaRegister();

	private ValueObjectMetaRegister valueObjectMetaRegister = new ValueObjectMetaRegister();

	protected RegisterCenter() {
		super();
	}

	public static RegisterCenter instance() {

		synchronized (lock) {
			if (single == null) {
				single = new RegisterCenter();
			}
		}

		return single;
	}

	public ClassMetaRegister getClassMetaRegister() {
		return classMetaRegister;
	}

	public EntityMetaRegister getEntityMetaRegister() {
		return entityMetaRegister;
	}

	public AggregateRootMetaRegister getAggregateRootMetaRegister() {
		return aggregateRootMetaRegister;
	}

	public ValueObjectMetaRegister getValueObjectMetaRegister() {
		return valueObjectMetaRegister;
	}
}
