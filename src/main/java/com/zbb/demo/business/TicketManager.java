package com.zbb.demo.business;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.util.StringUtils;
import com.zbb.demo.entities.Ticket;

import com.zbb.demo.repositories.ITicketDal;

@Service
public class TicketManager implements ITicketService {

	private ITicketDal ticketDal;

	@Autowired
	public TicketManager(ITicketDal ticketDal) {
		this.ticketDal = ticketDal;
	}

	@Override
	@Transactional
	public List<Ticket> getAll() {
		return ticketDal.getAll();
	}

	@Override
	@Transactional
	public void create(Ticket ticket) {
		this.ticketDal.create(ticket);
	}

	@Override
	@Transactional
	public void update(Ticket ticket, String id) {
		this.ticketDal.update(ticket, id);

	}

	@Override
	@Transactional
	public Ticket getById(String id) {
		return this.ticketDal.getById(id);
	}


}
