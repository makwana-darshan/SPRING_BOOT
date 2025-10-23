package com.jsp;

import java.security.PublicKey;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@GetMapping("/home")
	public String getEmployee() {

		return "Welcome to home page ";
	}

	@GetMapping("/employee")
	public String getEmployee(@RequestParam String name, @RequestParam int id) {

		return "name -> :" + name + " id-> " + id;
	}

	@GetMapping("/student/{name}/{marks}")
	public String getEmployee(@PathVariable String name, @PathVariable Double marks) {
		return "name -> :" + name + " id-> " + marks;
	}
	
	@GetMapping("/application")
	public String getApp(@RequestHeader double appVersion,@RequestHeader String appName) {
		return "appname : "+ appName+"\nappversion : "+appVersion;
	}
	
	@PostMapping("/employee")
	public String getEmp(@RequestBody Employee employee) {
		return employee.toString();
	}
	
//	@PostMapping("/employee")
//	public Employee getEmp(@RequestBody Employee employee) {
//		return employee;
//	}
	
	
}
