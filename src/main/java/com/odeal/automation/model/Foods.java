package com.odeal.automation.model;

import java.io.Serializable;

import com.odeal.automation.enums.PaymentType;

public class Foods implements Serializable,Machine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 727041550257939484L;
	private int numberOfFoods; //adet
	private static Double unitPrice = 2.00; // birim fiyat
	private static int maxCount = 20; // 20 adet yiyecek olmali.
	private String paymentType; // odeme tipi
	private Double amount; // gelen para
	
	public Foods(int numberOfFoods, String paymentType, Double amount) {
		super();
		this.numberOfFoods = numberOfFoods;
		this.paymentType = paymentType;
		this.amount = amount;
		maxCount = maxCount - numberOfFoods;
	}

	public int getNumberOfFoods() {
		return numberOfFoods;
	}

	public void setNumberOfFoods(int numberOfFoods) {
		this.numberOfFoods = numberOfFoods;
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
		result = prime * result + numberOfFoods;
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
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
		Foods other = (Foods) obj;
		if (numberOfFoods != other.numberOfFoods)
			return false;
		if (paymentType == null) {
			if (other.paymentType != null)
				return false;
		} else if (!paymentType.equals(other.paymentType))
			return false;
		return true;
	}

	@Override
	public AutomationResponse printReceipt(AutomationRequest request) {
		AutomationResponse response = new AutomationResponse();
		String paymentType = "";
		response.setProductName("Foods");
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
		return unitPrice * this.numberOfFoods;
	}

	@Override
	public Double refundAmount() {
		return this.amount - (unitPrice * this.numberOfFoods);
	}

	@Override
	public int getMaxCount() {
		return maxCount;
	}
	
	

}
