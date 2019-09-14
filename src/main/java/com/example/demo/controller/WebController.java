package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.repo.CustomerRepository;

@RestController
public class WebController {

	@Autowired
	CustomerRepository repository;
	
	@RequestMapping("/save")
	public String addMockup() {
		repository.save(new Customer("shimon"));
		repository.save(new Customer("shimon1"));
		repository.save(new Customer("shimon2"));
		repository.save(new Customer("shimon3"));
		
		return "mockup created!!!";
	}
	
	@RequestMapping("/all")
	public String findAll() {
		StringBuilder result = new StringBuilder("<html>");
		for (Customer cust : repository.findAll()) {
			result.append("<div>");
			result.append(cust.toString());
			result.append("</div>");
		}
		return result.append("</html>").toString();
	}
	
	@RequestMapping("/findname")
	public String findByName(@RequestParam("name") String name) {
		StringBuilder result = new StringBuilder("<html>");
		for (Customer cust : repository.findByName(name)) {
			result.append("<div>");
			result.append(cust.toString());
			result.append("</div>");
		}
		return result.append("</html>").toString();
	}
}
