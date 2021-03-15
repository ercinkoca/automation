package com.odeal.automation.model;

import java.io.Serializable;

public class AutomationResponse implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8134412013619079499L;
	
	private String productName;
	private int quantity;
	private String paymentType;
	private String refundedAmount;
	
	public AutomationResponse() {
		super();
	}
	
	public AutomationResponse(String productName, int quantity, String paymentType, String refundedAmount) {
		super();
		this.productName = productName;
		this.quantity = quantity;
		this.paymentType = paymentType;
		this.refundedAmount = refundedAmount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getRefundedAmount() {
		return refundedAmount;
	}

	public void setRefundedAmount(String refundedAmount) {
		this.refundedAmount = refundedAmount;
	}
	
	

}
