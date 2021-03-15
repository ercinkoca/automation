package com.odeal.automation.unit.tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.odeal.automation.model.AutomationRequest;
import com.odeal.automation.model.AutomationResponse;
import com.odeal.automation.model.Foods;
import com.odeal.automation.model.Machine;

@SpringBootTest
public class FoodsTests {
	
	private Machine product;
	private AutomationRequest request = new AutomationRequest();
	private AutomationResponse response = new AutomationResponse();
	
	@BeforeEach
	public void setUp() {
		request.setAmount(8.00);
		request.setPaymentType(2);
		request.setProductType("Food");
		request.setQuantity(3);
		product = new Foods(request.getQuantity(), String.valueOf(request.getPaymentType()), request.getAmount());
	}
	
	@Test
	public void minimumPayAmountTest() {
		assertTrue(product.minimumPayAmount() >= 6.00);
	}
	
	@Test
	public void refundedAmountTest() {
		assertTrue(product.refundAmount() >= 2.00);
	}
	
	@Test
	public void printReceiptTest() {
		response = product.printReceipt(request);
		assertAll("Foods",
		        () -> assertEquals("Kredi Kartı - Temassız", response.getPaymentType()),
		        () -> assertEquals("Foods", response.getProductName()),
		        () -> assertEquals(3, response.getQuantity()),
		        () -> assertEquals("2.0", product.refundAmount().toString())
		    );
	}
	
	
	@Test
	public void maxCountTest() {
		assertTrue(product.getMaxCount() == 11); // buradan once 3 kere nesne yarattigi icin 11 olmali.
	}

}
