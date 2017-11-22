package com.mystore.common.meta;

import com.mystore.common.meta.domain.AggregateRootMetaRegister;
import com.mystore.common.meta.domain.SubEntityMetaRegister;
import com.mystore.common.meta.domain.ValueObjectMetaRegister;

public class RegisterCenter {

	private static Object lock = new Object();

	private static RegisterCenter single;

	private ClassMetaRegister classMetaRegister = new ClassMetaRegister();

	private TableRegister tableRegister = new TableRegister();

	// private EntityMetaRegister entityMetaRegister = new EntityMetaRegister();

	private AggregateRootMetaRegister aggregateRootMetaRegister = new AggregateRootMetaRegister();

	private SubEntityMetaRegister subEntityMetaRegister = new SubEntityMetaRegister();

	private ValueObjectMetaRegister valueObjectMetaRegister = new ValueObjectMetaRegister();

	private RegisterCenter() {
		super();
	}

	protected static RegisterCenter instance() {

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

	public TableRegister getTableRegister() {
		return tableRegister;
	}

	// public EntityMetaRegister getEntityMetaRegister() {
	// return entityMetaRegister;
	// }

	public AggregateRootMetaRegister getAggregateRootMetaRegister() {
		return aggregateRootMetaRegister;
	}

	public SubEntityMetaRegister getSubEntityMetaRegister() {
		return subEntityMetaRegister;
	}

	public ValueObjectMetaRegister getValueObjectMetaRegister() {
		return valueObjectMetaRegister;
	}
}
