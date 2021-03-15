package com.odeal.automation.unit.tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.odeal.automation.model.AutomationRequest;
import com.odeal.automation.model.AutomationResponse;
import com.odeal.automation.model.Drinks;
import com.odeal.automation.model.Machine;

@SpringBootTest
public class DrinksTests {
	
	
	private Machine product;
	private AutomationRequest request = new AutomationRequest();
	private AutomationResponse response = new AutomationResponse();
	
	@BeforeEach
	public void setUp() {
		request.setAmount(4.00);
		request.setPaymentType(1);
		request.setProductType("Drink");
		request.setQuantity(3);
		request.setSugarCount(0);
		product = new Drinks(request.getQuantity(), request.getSugarCount(), String.valueOf(request.getPaymentType()), request.getAmount());
	}
	
	@Test
	public void minimumPayAmountTest() {
		assertTrue(product.minimumPayAmount() >= 3.00);
	}
	
	@Test
	public void refundedAmountTest() {
		assertTrue(product.refundAmount() >= 1.00);
	}
	
	@Test
	public void printReceiptTest() {
		response = product.printReceipt(request);
		assertAll("Drinks",
		        () -> assertEquals("Kredi Kartı - Temaslı", response.getPaymentType()),
		        () -> assertEquals("Drinks", response.getProductName()),
		        () -> assertEquals(3, response.getQuantity()),
		        () -> assertEquals("1.0", product.refundAmount().toString())
		    );
	}
	
	
	@Test
	public void maxCountTest() {
		assertTrue(product.getMaxCount() == 1); // buradan once 3 kere nesne yarattigi icin 1 olmali.
	}
	
	
}
