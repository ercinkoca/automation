package com.odeal.automation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.odeal.automation.model.AutomationRequest;
import com.odeal.automation.model.AutomationResponse;
import com.odeal.automation.service.AutomationService;

@RestController
public class AutomationController {
	
	@Autowired
	private AutomationService service;
	
	@PostMapping(value="/give-product",produces=MediaType.APPLICATION_JSON_VALUE)
	public AutomationResponse giveProduct(@RequestBody AutomationRequest request) {
		return service.giveProduct(request);
	}

}
