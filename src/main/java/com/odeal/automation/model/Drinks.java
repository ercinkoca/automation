package com.odeal.automation.model;

import java.io.Serializable;

import com.odeal.automation.enums.PaymentType;

public class Drinks implements Serializable,Machine {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6066546954857095169L;
	private int numberOfDrinks; //adet
	private static Double unitPrice = 1.00; // birim fiyat
	private static int maxCount = 10; // 10 adet içecek olmali.
	private int sugarCount; // sicak iceceklerde seker adeti.
	private String paymentType; // odeme tipi
	private Double amount; // gelen para
	
	public Drinks(int numberOfDrinks, int sugarCount, String paymentType, Double amount) {
		super();
		this.numberOfDrinks = numberOfDrinks;
		this.sugarCount = sugarCount;
		this.paymentType = paymentType;
		this.amount = amount;
		maxCount = maxCount - numberOfDrinks;		
	}

	public int getNumberOfDrinks() {
		return numberOfDrinks;
	}

	public void setNumberOfDrinks(int numberOfDrinks) {
		this.numberOfDrinks = numberOfDrinks;
	}

	public int getSugarCount() {
		return sugarCount;
	}

	public void setSugarCount(int sugarCount) {
		this.sugarCount = sugarCount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numberOfDrinks;
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
		result = prime * result + sugarCount;
		result = prime * result + ((unitPrice == null) ? 0 : unitPrice.hashCode());
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
		Drinks other = (Drinks) obj;
		if (numberOfDrinks != other.numberOfDrinks)
			return false;
		if (paymentType == null) {
			if (other.paymentType != null)
				return false;
		} else if (!paymentType.equals(other.paymentType))
			return false;
		if (sugarCount != other.sugarCount)
			return false;
		return true;
	}

	@Override
	public AutomationResponse printReceipt(AutomationRequest request) {
		AutomationResponse response = new AutomationResponse();
		String paymentType = "";
		response.setProductName("Drinks");
		response.setQuantity(request.getQuantity());
		if(request.getPaymentType() == 1) {
			paymentType = PaymentType.KREDIKARTI_TEMASLI.getResult();
		} else if( request.getPaymentType() == 2) {
			paymentType = PaymentType.KREDIKARTI_TEMASSIZ.getResult();
		} else if(request.getPaymentType() == 3 ) {
			paymentType = PaymentType.NAKIT_KAGIT.getResult();
		}else {
			paymentType = PaymentType.NAKIT_BOZUK.getResult();
		}
		response.setPaymentType(paymentType);
		response.setRefundedAmount(refundAmount().toString());
		return response;
	}

	@Override
	public Double minimumPayAmount() {
		return unitPrice * this.numberOfDrinks;
	}

	@Override
	public Double refundAmount() {
		return this.amount - (unitPrice * this.numberOfDrinks);
	}

	@Override
	public int getMaxCount() {
		return maxCount;
	}

}
