package com.odeal.automation.unit.tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.odeal.automation.exceptions.BadRequestException;
import com.odeal.automation.model.AutomationRequest;
import com.odeal.automation.service.AutomationService;

@SpringBootTest
public class ServiceTests {
	
	@Autowired
	private AutomationService service;
	private AutomationRequest request = new AutomationRequest();
	
	@BeforeEach
	public void setUp() {
		request.setAmount(1.00);
		request.setPaymentType(1);
		request.setProductType("");
		request.setQuantity(1);
	}
	
	@Test
	public void validateRequestTests() {
		Exception exception = assertThrows(BadRequestException.class, () -> {
			service.validateRequest(request);
		});
	    assertTrue(exception.getMessage().equals("Product Type Boş olamaz!"));
	}

}
