package com.zbb.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zbb.demo.entities.Message;
import com.zbb.demo.repositories.MessageRepository;

@Controller
public class TicketController {
	  @Autowired
	    private MessageRepository messageRepository;

	    @GetMapping("/ticket")
	    public String ticket(Model model) {
	        model.addAttribute("msgs", messageRepository.findAll());
	        return "/new_ticket";
	    }

	    @PostMapping("/messages")
	    public String save(Message message) {
	        messageRepository.save(message);
	        return "redirect:/ticket";
	    }
}