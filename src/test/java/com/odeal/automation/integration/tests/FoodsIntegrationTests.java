package com.odeal.automation.integration.tests;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.odeal.automation.controller.AutomationController;
import com.odeal.automation.model.AutomationRequest;
import com.odeal.automation.model.AutomationResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(AutomationController.class)
public class FoodsIntegrationTests {
	
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private AutomationController automationController;
	
	@Test
	public void giveProductTest() throws Exception {
	    AutomationRequest request = new AutomationRequest();
	    AutomationResponse response = new AutomationResponse();
	    request.setAmount(8.00);
	    request.setPaymentType(3);
	    request.setProductType("Food");
	    request.setQuantity(4);
	    response.setPaymentType("Nakit İle - Kağıt");
	    response.setProductName("Foods");
	    response.setQuantity(4);
	    response.setRefundedAmount("0.0");
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    given(automationController.giveProduct(request)).willReturn(response);
	    
	    try {
			String requestJson=ow.writeValueAsString(request);
			mvc.perform(MockMvcRequestBuilders
				      .post("/give-product")
				      .content(requestJson)
				      .contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().isOk())
				      .andExpect(MockMvcResultMatchers.jsonPath("$.productName").exists())
				      .andExpect(MockMvcResultMatchers.jsonPath("$.refundedAmount").exists())
				      .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", is(response.getQuantity())));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	    
	    
	    
	}

}
