package com.zbb.demo.business;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zbb.demo.entities.Ticket;

public interface ITicketService {
	List<Ticket> getAll();

	void create(Ticket ticket);

	void update(Ticket ticket, String id);

	Ticket getById(String id);
}
