package com.zbb.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.zbb.demo.business.ITicketService;
import com.zbb.demo.business.IUserService;
import com.zbb.demo.entities.Message;
import com.zbb.demo.entities.Ticket;

import com.zbb.demo.repositories.MessageRepository;
import com.zbb.demo.web.dto.UserRegistrationDto;

@RestController

public class TicketController {
	private ITicketService ticketService;
	
	@Autowired
	public TicketController(ITicketService ticketService) {
		this.ticketService = ticketService;
	}
	
	
	@GetMapping("/tickets")
	public ResponseEntity<List<Ticket>> getTickets() {
		return new ResponseEntity<>(ticketService.getAll(), HttpStatus.OK);
	}

	@PostMapping("/tickets")
	public RedirectView createTicket(@ModelAttribute("ticket") Ticket ticket) {
		
		ticketService.create(ticket);
		return new RedirectView("/viewTickets");
		//return new ResponseEntity<>("Ticket is created successfully", HttpStatus.CREATED);
	}

	@PutMapping("/ticket/{id}")
	public ResponseEntity<Object> updateProduct(@RequestBody Ticket ticket, @PathVariable String id) {
		if (ticketService.getById(id) != null) {
			ticketService.update(ticket, id);
			return new ResponseEntity<>("Ticket is updated successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Ticket is not found", HttpStatus.NOT_FOUND);

	}

	
	
	
	
	
	
	    
	 
}