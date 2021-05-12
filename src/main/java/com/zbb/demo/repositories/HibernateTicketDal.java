package com.zbb.demo.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.zbb.demo.entities.Ticket;

@Repository
public class HibernateTicketDal implements ITicketDal {
	private EntityManager entityManager;

	@Autowired
	public HibernateTicketDal(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<Ticket> getAll() {
		Session session = entityManager.unwrap(Session.class);
		List<Ticket> ticket = session.createQuery("from Ticket", Ticket.class).getResultList();
		return ticket;
	}
	
	@Override
	public void create(Ticket ticket) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Session session = entityManager.unwrap(Session.class);
	
		ticket.setUserName(currentPrincipalName);
		
		
		session.saveOrUpdate(ticket);

	}

	@Override
	public void update(Ticket ticket, String id) {
		Session session = entityManager.unwrap(Session.class);
		Ticket ticketToUpdate = session.get(Ticket.class, id);

		ticketToUpdate.setId(ticket.getId());
		ticketToUpdate.setName(ticket.getName());
	
		ticketToUpdate.setCategory(ticket.getCategory());
		ticketToUpdate.setStatus(ticket.getStatus());

	}

	@Override
	public Ticket getById(String id) {
		Session session = entityManager.unwrap(Session.class);
		Ticket ticket = session.get(Ticket.class, id);
		return ticket;
	}
}