package com.mystore.common.meta;

public abstract class RegisterCenterFactory {

	protected RegisterCenter registerCenter = RegisterCenter.instance();

	public RegisterCenterFactory() {
		init();
	}

	abstract protected void init();

	public RegisterCenter getRegisterCenter() {
		return registerCenter;
	}

}
