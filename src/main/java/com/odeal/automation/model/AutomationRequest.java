package com.odeal.automation.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class AutomationRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6873322525356295703L;
	@ApiModelProperty(example = "Drink",required = true,dataType = "String",notes = "Product Type (Drink or Food)")
	private String productType; // urun cesidi (food or drink)
	@ApiModelProperty(example = "1.00",required = true,dataType = "Double",notes = "Given amount")
	private Double amount; // ne kadar para girildigi
	@ApiModelProperty(example = "3",required = true,dataType = "int",notes = "Quantity of Product")
	private int quantity; // miktar
	@ApiModelProperty(example = "1",required = true,dataType = "int",notes = "Payment Type of Product(1,2,3,4)")
	private int paymentType; // Odeme tipi (1,2,3)
	@ApiModelProperty(example = "1",required = true,dataType = "int",notes = "Sugar Count of hot drinks")
	private int sugarCount; // sicak iceceklerde seker sayisi
	
	public AutomationRequest() {
		super();
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	public int getSugarCount() {
		return sugarCount;
	}

	public void setSugarCount(int sugarCount) {
		this.sugarCount = sugarCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + paymentType;
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		result = prime * result + quantity;
		result = prime * result + sugarCount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AutomationRequest other = (AutomationRequest) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (paymentType != other.paymentType)
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		if (quantity != other.quantity)
			return false;
		if (sugarCount != other.sugarCount)
			return false;
		return true;
	}
	
	

}
