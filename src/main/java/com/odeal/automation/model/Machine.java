package com.odeal.automation.model;

public interface Machine {
	
	public AutomationResponse printReceipt(AutomationRequest request);
	public Double minimumPayAmount();
	public Double refundAmount();
	public int getMaxCount();

}
