package com.zbb.demo.business;

import java.awt.desktop.UserSessionListener;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zbb.demo.entities.Role;
import com.zbb.demo.entities.User;
import com.zbb.demo.repositories.UserRepository;
import com.zbb.demo.web.dto.UserRegistrationDto;
@Service
public class UserManager implements IUserService {
	   @Autowired
	    private UserRepository userRepository;
	   
	   @Bean
	    public PasswordEncoder passEncoder() {
	        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	    }
	//	private BCryptPasswordEncoder passwordEncoder;

		public UserManager(UserRepository userRepository) {
			super();
			this.userRepository = userRepository;
		}

	    @Override
	    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	        User user = userRepository.findByEmail(userName).orElseThrow(() -> new UsernameNotFoundException("Email " + userName + " not found"));
	         return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
	         getAuthorities(user));
	    }

	    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
	        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
	        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
	        return authorities;
	    }
//passwordEncoder.encode(registrationDto.getPassword()),
		@Override
		public User save(UserRegistrationDto registrationDto) {
			User user = new User(registrationDto.getName(), 
			registrationDto.getEmail(),passEncoder().encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
			System.out.println(user);
			return userRepository.save(user);
		}
}
