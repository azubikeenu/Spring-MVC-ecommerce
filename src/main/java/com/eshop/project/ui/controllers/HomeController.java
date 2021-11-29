package com.eshop.project.ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("")
	String showIndex() {
		return "index.html";
	}

	@GetMapping("/login")
	String showLogin() {
		return "login.html";
	}
}
