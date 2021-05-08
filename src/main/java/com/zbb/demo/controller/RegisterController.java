package com.zbb.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import com.zbb.demo.entities.Message;
import com.zbb.demo.entities.User;
import com.zbb.demo.repositories.MessageRepository;

@Controller
public class RegisterController {

	@GetMapping("/register")
	public String showRegistrationForm(WebRequest request, Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}

}
