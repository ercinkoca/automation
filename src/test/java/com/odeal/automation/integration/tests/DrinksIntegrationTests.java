package com.odeal.automation.integration.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
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

@RunWith(SpringRunner.class)
@WebMvcTest(AutomationController.class)
public class DrinksIntegrationTests {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private AutomationController automationController;
	
	@Test
	public void giveProductTest() throws Exception {
	    AutomationRequest request = new AutomationRequest();
	    AutomationResponse response = new AutomationResponse();
	    request.setAmount(3.00);
	    request.setPaymentType(2);
	    request.setProductType("Drink");
	    request.setQuantity(1);
	    request.setSugarCount(0);
	    response.setPaymentType("Kredi Kartı - Temassız");
	    response.setProductName("Drinks");
	    response.setQuantity(1);
	    response.setRefundedAmount("3.0");
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
				      .andExpect(MockMvcResultMatchers.jsonPath("$.productName", is(response.getProductName())))
				      .andExpect(MockMvcResultMatchers.jsonPath("$.refundedAmount", is(response.getRefundedAmount())))
				      .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", is(response.getQuantity())));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}
}
