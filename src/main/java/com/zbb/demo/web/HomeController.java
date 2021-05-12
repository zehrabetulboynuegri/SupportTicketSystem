package com.zbb.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zbb.demo.entities.Message;
import com.zbb.demo.repositories.MessageRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	
    @GetMapping
    public String home() {
  
        return "userhome";
    }

}