package com.zbb.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zbb.demo.business.ITicketService;
import com.zbb.demo.entities.Ticket;
import com.zbb.demo.web.dto.UserRegistrationDto;

@Controller
@RequestMapping
public class UserController {
	@Autowired
	private ITicketService ticketService;
	
	
	 @RequestMapping(value = "/ticket/add", method = RequestMethod.GET)
	    public String add(Model model) {
	        model.addAttribute("ticket", new Ticket());
	        return "new_ticket";
	    }
	 @RequestMapping(value = "/ticket/{id}", method = RequestMethod.GET)
	    public ModelAndView update(@PathVariable(name = "id") String id) {
	        ModelAndView mav = new ModelAndView("update_ticket");
	        Ticket ticket = ticketService.getById(id);
	        mav.addObject("ticket", ticket);
	         
	        return mav;
	    }
	@RequestMapping("/viewTickets")
	public String tickets(Model model) {
		List<Ticket> listTicket = ticketService.getAll();
		model.addAttribute("listTicket", listTicket);

		return "/ticket_list";
	}

}
