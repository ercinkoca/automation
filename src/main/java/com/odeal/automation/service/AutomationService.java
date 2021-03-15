package com.odeal.automation.service;

import org.springframework.stereotype.Service;

import com.odeal.automation.exceptions.BadRequestException;
import com.odeal.automation.model.AutomationRequest;
import com.odeal.automation.model.AutomationResponse;
import com.odeal.automation.model.Drinks;
import com.odeal.automation.model.Foods;
import com.odeal.automation.model.Machine;

@Service
public class AutomationService {

	public AutomationResponse giveProduct(AutomationRequest request) {
		
		validateRequest(request);
		AutomationResponse response = new AutomationResponse();
		Machine product;

		if (request.getProductType().equals("Drink")) {
			product = new Drinks(request.getQuantity(), request.getSugarCount(),String.valueOf(request.getPaymentType()), request.getAmount());
		} else {
			product = new Foods(request.getQuantity(), String.valueOf(request.getPaymentType()), request.getAmount());
		}
		
		if (product.getMaxCount() < request.getQuantity()) {
			throw new BadRequestException("Stoklarimizda istediginiz adet kadar urunumuz bulunmamaktadir.");
		}

		if (product.minimumPayAmount().intValue() > request.getAmount().intValue()) {
			throw new BadRequestException(
					"Eksik Para Girisi : Minimum odenmesi gereken tutar: " + product.minimumPayAmount().toString());
		}
		response = product.printReceipt(request);
		return response;
	}
	
	public void validateRequest(AutomationRequest request) {
		if (request.getProductType().isEmpty()) {
			throw new BadRequestException("Product Type Boş olamaz!");
		}
		if (request.getAmount() == null) {
			throw new BadRequestException("Amount Boş olamaz!");
		}
		if (request.getPaymentType() == 0) {
			throw new BadRequestException("PaymentType 0 olamaz!");
		}
		if (request.getQuantity() == 0) {
			throw new BadRequestException("Quantity 0 olamaz!");
		}
	}

}
