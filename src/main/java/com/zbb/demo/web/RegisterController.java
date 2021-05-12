package com.zbb.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.zbb.demo.business.IUserService;
import com.zbb.demo.business.UserManager;
import com.zbb.demo.entities.Message;
import com.zbb.demo.entities.User;
import com.zbb.demo.repositories.MessageRepository;
import com.zbb.demo.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class RegisterController {
	private IUserService userService;

	public RegisterController(IUserService userService) {
		super();
		this.userService = userService;
	}
	@GetMapping
	public String showRegistrationForm() {
	
		return "registration";
	}
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
	
}
