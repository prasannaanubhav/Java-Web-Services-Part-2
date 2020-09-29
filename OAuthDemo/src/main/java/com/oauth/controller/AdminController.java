package com.oauth.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

	@RequestMapping("/admin/{name}")
	public String admin(@PathVariable("name") String name) {

		return "Hello " + name;
	}
}
