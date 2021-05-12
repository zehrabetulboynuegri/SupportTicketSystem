package com.zbb.demo.business;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.zbb.demo.entities.User;
import com.zbb.demo.web.dto.UserRegistrationDto;

public interface IUserService extends UserDetailsService
{
	User save(UserRegistrationDto registrationDto);
}
