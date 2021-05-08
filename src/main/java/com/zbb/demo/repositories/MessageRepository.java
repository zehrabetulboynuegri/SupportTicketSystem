package com.zbb.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zbb.demo.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{

}