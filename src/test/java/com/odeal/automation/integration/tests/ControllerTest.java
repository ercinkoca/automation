package com.odeal.automation.integration.tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.odeal.automation.controller.AutomationController;
import com.odeal.automation.model.AutomationRequest;
import com.odeal.automation.model.AutomationResponse;

@SpringBootTest
public class ControllerTest {
	
	@Autowired
	private AutomationController automationController;
	
	private AutomationRequest request = new AutomationRequest();
	private AutomationResponse response = new AutomationResponse();
	
	@BeforeEach
	public void setUp() {
		request.setAmount(8.00);
		request.setPaymentType(3);
		request.setProductType("Food");
		request.setQuantity(4);
	}
	
	@Test
	public void controllerTest() {
		response = automationController.giveProduct(request);
		assertTrue(response.getRefundedAmount().equals("0.0"));
	}
	
	
	@Test
	public void controllerTest2() {
		response = automationController.giveProduct(request);
		assertTrue(response.getPaymentType().equals("Nakit İle - Kağıt"));
	}
	
	
	
	
	

}
