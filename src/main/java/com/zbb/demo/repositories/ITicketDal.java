package com.zbb.demo.repositories;

import java.util.List;


import com.zbb.demo.entities.Ticket;

public interface ITicketDal {

	List<Ticket> getAll();
	void create(Ticket ticket);
	void update(Ticket ticket,String id);
	Ticket getById(String id);
}
